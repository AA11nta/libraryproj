package com.example.libraryproj.library.repos;

import com.example.libraryproj.library.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface BookRepository extends CrudRepository<Book, Long> {

  //  public Book findBook(String title, String author, int numberOfPages);

}
