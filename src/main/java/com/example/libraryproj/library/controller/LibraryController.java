package com.example.libraryproj.library.controller;

import com.example.libraryproj.library.entities.Book;
import com.example.libraryproj.library.service.BookLendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LibraryController {
    private static final String BASE_PATH = "/library";

    @Autowired
    BookLendingService bookLendingService;

    @GetMapping(BASE_PATH + "/inventory")
    public String getAllTheBooks() {
        try {
            String message = String.format("Display all books :\n %s", bookLendingService.getAllBooks().toString());
            log.info(message);
            return message;
        } catch (Exception e) {
            log.error("Can not display all books", e);
            return e.getMessage();
        }
    }

    @PostMapping(BASE_PATH + "lendBook/{customerId}/{title}")
    public String lendBookToCustomerWithId(@PathVariable String customerId, @PathVariable String title) {
        try {
            log.info("Customer {} wants to borrow {}", customerId, title);
            Book book = bookLendingService.lendBookByTitle(title, customerId);
            String message = String.format("Book %s was borrowed by customer with id %s", book, customerId);
            log.info(message);
            return message;
        } catch (Exception e) {
            log.error("Something went wrong", e);
            return e.getMessage();
        }
    }

    @PostMapping(BASE_PATH + "returnBook/{customerId}/{title}")
    public String returnBook(@PathVariable String title, @PathVariable String customerId) {
        try {
            log.info("Customer {} wants to return {}", customerId, title);
            Book book = bookLendingService.returnBook(title, customerId);
            String message = String.format("Book %s was returned by customer with id %s", book, customerId);
            log.info(message);
            return message;
        } catch (Exception e) {
            log.error("Something went wrong", e);
            return e.getMessage();
        }
    }

    @GetMapping(BASE_PATH + "/getUserByName/{name}")
    public String getUserInfoByName(@PathVariable String name) throws Exception {
        return bookLendingService.getUserByName(name).toString();
    }

    @GetMapping(BASE_PATH + "/getUserById/{id}")
    public String getUserInfoById(@PathVariable String id) throws Exception {
        return bookLendingService.getUserById(id).toString();
    }

}

