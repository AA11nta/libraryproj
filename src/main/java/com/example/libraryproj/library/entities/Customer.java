package com.example.libraryproj.library.entities;

import lombok.Getter;

import java.util.Set;
import java.util.TreeSet;

@Getter
public class Customer {

    private String id;
    private String name;
    private Set<Book> borrowedBooks = new TreeSet<>();

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addToBorrowedBooks(Book b) {
        borrowedBooks.add(b);
    }

    public void removeFromBorrowedBooks(Book b) {
        borrowedBooks.remove(b);
    }

    public boolean hasAlreadyBorrowed(String title) {
        return borrowedBooks.stream().filter(book -> book.getTitle().equals(title)).findAny().isPresent();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
