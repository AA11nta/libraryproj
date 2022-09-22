package com.example.libraryproj.library.service;

import com.example.libraryproj.library.entities.Book;
import com.example.libraryproj.library.entities.Customer;
import com.example.libraryproj.library.entities.Library;
import com.example.libraryproj.library.repos.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrationService {
    @Autowired
    Library library;

//    @Autowired
//    private BookRepository bookRepository;


    public Customer addNewCustomer(String id, String name) throws Exception {
        return library.addNewCustomer(new Customer(id, name));
    }

    public Book addnewBook(String author, String title, int numberOfPages, int numberOfCopies) {
        Book b = new Book(author, title, numberOfPages);
        library.addBook(b, numberOfCopies);
        return b;

    }

}
