package com.example.libraryproj.library.repos;

import com.example.libraryproj.library.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {



}
