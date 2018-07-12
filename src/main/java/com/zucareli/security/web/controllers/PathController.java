package com.zucareli.security.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {

     @RequestMapping("/login")
     public String login() {
        return "signin/signin";
     }

    @RequestMapping("/")
    public String home() {
        return "redirect:/api/hello-world";
    }
}
