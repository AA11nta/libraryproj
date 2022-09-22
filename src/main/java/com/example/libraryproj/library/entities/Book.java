package com.example.libraryproj.library.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Entity
public class Book implements Comparable<Book> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private final String author;
    private final String title;

    @EqualsAndHashCode.Exclude
    private int numberOfPages;

    @EqualsAndHashCode.Exclude
    private int numberOfCopies;

    public Book(String author, String title, int numberOfPages) {
        this.author = author;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(Book o) {
        return 0;
    }


}
