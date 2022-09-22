package com.example.libraryproj.library.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Entity
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "borrowedBooks",
            joinColumns = @JoinColumn(name = "customerId"),
            inverseJoinColumns = @JoinColumn(name = "bookId")
    )
    private Set<Book> borrowedBooks = new TreeSet<>();

    public Customer(String id, String name) {
        this.id = Long.valueOf(id);
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
