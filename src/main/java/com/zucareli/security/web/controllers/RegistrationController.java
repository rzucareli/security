package com.zucareli.security.web.controllers;

import com.zucareli.security.service.UserService;
import com.zucareli.security.validation.EmailExistsException;
import com.zucareli.security.web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping("/signup")
    public ModelAndView registrationForm() {
        return new ModelAndView("signup/signup", "user", new User());
    }

    @RequestMapping(value = "user/register")
    public  ModelAndView registerUser(@Valid final User user, final BindingResult result) {
        if(result.hasErrors()) {
            return new ModelAndView( "signup/signup", "user", user);
        }
        try {
            userService.registerNewUser(user);
        } catch (EmailExistsException e) {
            result.addError(new FieldError("user", "email", e.getMessage()));
            return new ModelAndView("signup/signup", "user", user);
        }
        return new ModelAndView("redirect:/login");
    }

}
