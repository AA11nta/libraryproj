package com.example.libraryproj.library.service;

import com.example.libraryproj.library.entities.Book;
import com.example.libraryproj.library.entities.Customer;
import com.example.libraryproj.library.entities.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class BookLendingService {

    @Autowired
    private Library library;

    public Book lendBookByTitle(String title, String customerId) throws Exception {
        return library.lendBookWithTitleToCustomer(title, customerId);
    }

    public Map<Book, Integer> getAllBooks() {
        return library.getInventory();
    }

    public Book returnBook(String title, String customerId) throws Exception {
        return library.returnBookWithTitleFromCustomer(title, customerId);
    }

    public Customer getUserById(String id) throws Exception {
        Optional<Customer> customerOptional = library.getCustomers().stream().filter(customer -> customer.getId().equals(id)).findAny();
        if (!customerOptional.isPresent()) {
            throw new Exception("The user with id " + id + " could not be found");
        }
        return customerOptional.get();
    }


    public Customer getUserByName(String name) throws Exception {
        Optional<Customer> customerOptional = library.getCustomers().stream().filter(customer -> customer.getName().equals(name)).findAny();
        if (!customerOptional.isPresent()) {
            throw new Exception("The user with name " + name + " could not be found");
        }
        return customerOptional.get();
    }
}

