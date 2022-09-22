package com.example.libraryproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.libraryproj.library.repos")
@EntityScan("com.example.libraryproj.library.entities")
public class LibraryprojApplication {

    public static void main(String[] args) {

		SpringApplication.run(LibraryprojApplication.class, args);

    }

}
