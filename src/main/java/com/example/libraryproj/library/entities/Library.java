package com.example.libraryproj.library.entities;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter
public class Library {

    private List<Customer> customers = new ArrayList<>();

    private Map<Book, Integer> inventory = new HashMap<>();

    private final int maxNumberOfCustomers = 12;

    public Book lendBookWithTitleToCustomer(String title, String customerId) throws Exception {
        Customer customer = validateAndGetCustomer(customerId);
        if (customer.hasAlreadyBorrowed(title)) {
            throw new Exception("This customer has already borrowed " + title);

        }
        Map.Entry<Book, Integer> bookEntry = validateAndGetBookFromInventory(title);
        return lendBookIfCopiesAvailable(title, customer, bookEntry);
    }

    private Book lendBookIfCopiesAvailable(String title, Customer customer, Map.Entry<Book, Integer> bookEntry) throws Exception {
        if (bookEntry.getValue() >= 1) {
            removeFromInventory(bookEntry);
            return lendTheBook(customer, bookEntry.getKey());
        } else {
            throw new Exception("No more available copies for " + title);
        }

    }

    public void addBook(Book b, int numberOfCopies) {
        inventory.putIfAbsent(b, numberOfCopies);
    }

    public Book returnBookWithTitleFromCustomer(String title, String customerId) throws Exception {
        Customer customer = validateAndGetCustomer(customerId);
        Book book = getBookIfBorrowedByCustomer(title, customerId, customer);
        customer.removeFromBorrowedBooks(book);
        returnBookToInventory(book);
        return book;
    }

    public Customer addNewCustomer(Customer c) throws Exception {
        if (customers.size() < maxNumberOfCustomers) {
            customers.add(c);
            return c;
        }
        throw new Exception("Max customers reached. Customer " + c + " could not be added");
    }

    private Map.Entry<Book, Integer> validateAndGetBookFromInventory(String title) throws Exception {
        Optional<Map.Entry<Book, Integer>> bookEntry = findBook(title);
        if (!bookEntry.isPresent()) {
            throw new Exception("Book with title " + title + " not found in inventory.");
        }
        return bookEntry.get();
    }


    private Customer validateAndGetCustomer(String customerId) throws Exception {
        Optional<Customer> customer = findCustomer(customerId);
        if (!customer.isPresent()) {
            throw new Exception("Customer with id " + customerId + " not found.");
        }
        return customer.get();
    }

    private void returnBookToInventory(Book book) {
        inventory.put(book, inventory.get(book) + 1);
    }

    private Book getBookIfBorrowedByCustomer(String title, String customerId, Customer customer) throws Exception {
        Optional<Book> bookOptional = customer.getBorrowedBooks().stream().filter(b -> b.getTitle().equals(title)).findAny();
        if (!bookOptional.isPresent()) {
            throw new Exception("Book with title " + title + " was not borrowed by customer with id " + customerId);
        }
        return bookOptional.get();

    }

    private Book lendTheBook(Customer customer, Book book) {
        customer.addToBorrowedBooks(book);
        return book;
    }

    private void removeFromInventory(Map.Entry<Book, Integer> bookIntegerEntry) {
        bookIntegerEntry.setValue(bookIntegerEntry.getValue() - 1);
    }

    private Optional<Customer> findCustomer(String customerId) {
        return customers.stream().filter(c -> c.getId().equals(customerId)).findAny();
    }

    private Optional<Map.Entry<Book, Integer>> findBook(String title) {
        return inventory.entrySet().stream()
                .filter(bookIntegerEntry -> bookIntegerEntry.getKey().getTitle().equals(title)).findAny();
    }
}
