package com.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/all")
    public String getAll(){
        String result = webClientBuilder.build()
            .get()
            .uri("http://worker-service/worker/get")
            .retrieve()
            .bodyToMono(String.class)
            .block();

        if(result.isBlank()){
            return "all";
        }

        return result;
    }

    @PostMapping("/create/{name}")
    public String create(@PathVariable("name") String name){
        String result = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/worker/get")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(result);
        return name+"\n"+result;
    }
}
