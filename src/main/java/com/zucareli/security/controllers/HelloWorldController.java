package com.zucareli.security.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello-world")
public class HelloWorldController {

    @RequestMapping
    public String helloWorld() {
        return "Hello World";
    }

}
