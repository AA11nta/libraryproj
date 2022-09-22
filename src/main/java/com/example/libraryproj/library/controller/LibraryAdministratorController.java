package com.example.libraryproj.library.controller;

import com.example.libraryproj.library.entities.Book;
import com.example.libraryproj.library.entities.Customer;
import com.example.libraryproj.library.repos.BookRepository;
import com.example.libraryproj.library.repos.CustomerRepository;
import com.example.libraryproj.library.service.AdministrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LibraryAdministratorController {

    private static final String BASE_PATH = "/library/manage";

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    private AdministrationService administrationService;


    @PutMapping(BASE_PATH + "/addNewBook")
    public String addNewBook(@RequestParam String author, @RequestParam String title,
                             @RequestParam int numberOfPages, @RequestParam int numberOfCopies) {
        log.info("Trying to add new book");
        Book book = administrationService.addnewBook(author, title, numberOfPages, numberOfCopies);
        String message = String.format("Book %s successfully added", book);
        log.info(message);
        bookRepository.save(book);
        return message;
    }

    @PutMapping(BASE_PATH + "/addNewCustomer")
    public String addNewCustomer(@RequestParam String id, @RequestParam String name) {
        log.info("Trying to add new customer with id {} and name {} ", id, name);
        try {
            Customer c = administrationService.addNewCustomer(id, name);
            customerRepository.save(c);
            return "Customer " + c + " was successfully added.";

        } catch (Exception e) {
            String message = String.format("can not add customer %s : %s", name, e.getMessage());
            return message;

        }

    }
}
