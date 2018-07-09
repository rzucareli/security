package com.zucareli.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {

     @RequestMapping("/login")
     public String login() {
        return "loginPage";
     }

    @RequestMapping("/")
    public String home() {
        return "redirect:/api/hello-world";
    }
}
