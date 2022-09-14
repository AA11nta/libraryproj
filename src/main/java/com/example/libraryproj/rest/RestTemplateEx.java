package com.example.libraryproj.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class RestTemplateEx {

    public static final String ADD_CUSTOMER_URL="http://localhost:8080/library/manage/addNewCustomer";

    @Autowired
    RestTemplate template;
    @Autowired
    RestTemplate webClient;

    @PutMapping
    public String addCustomerToLibrary(){
        String url = ADD_CUSTOMER_URL;

        String urlTemplate= getTemplate(url, dummyData());
        HttpEntity<?> entity = getHttpEntity();

        ResponseEntity<String> result = template.exchange(urlTemplate, HttpMethod.PUT,entity,String.class);
        if(result.getStatusCode().is2xxSuccessful()){
            return "Customer was added";
        }
        return "Failed to add customer";
    }

    private MultiValueMap<String,String> dummyData(){
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        map.add("id","newId");
        map.add("nume","newName");
        return map;
    }

//    @PutMapping("webClient/addNewCustomer")
//    public String addNewCustomer(){
//        return webClient.put()
//                .uri(uriBuilder->uriBuilder.path("localhost:8080/library/manage/addNewCustomer")
//                        .queryParams(dummyData())
//                        .build()
//                    )
//                    .accept(MediaType.APPLICATION_JSON)
//                    .retrieve()
//                    .bodyToMono(String.class)
//                    .block();
//            }

    private String getTemplate(String url,MultiValueMap map){
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParams(map)
                .encode()
                .toUriString();
        return urlTemplate;
    }

    private HttpEntity<?> getHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity =new HttpEntity<>(headers);
        return entity;
    }
}

