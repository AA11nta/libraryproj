package com.example.libraryproj.rest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Template {
    @Bean
    public RestTemplate getTemplate(){
        return new RestTemplateBuilder().build();
    }

    @Bean
    public WebClient getwebClient(){
        return WebClient.builder().build();
    }
}
