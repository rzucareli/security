package com.zucareli.security.authorization.controller

import com.zucareli.security.authorization.configuration.validation.EmailExistsException
import com.zucareli.security.authorization.domain.User
import com.zucareli.security.authorization.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import javax.validation.Valid

@Controller
class RegistrationController {

    @Autowired
    UserService userService


    @RequestMapping("/signup")
    ModelAndView registrationForm() {
        return new ModelAndView("signup/signup", "user", new User())
    }

    @RequestMapping(value = "user/register")
    ModelAndView registerUser(@Valid final User user, final BindingResult result) {
        if(result.hasErrors()) {
            return new ModelAndView( "signup/signup", "user", user)
        }
        try {
            userService.registerNewUser(user)
        } catch (EmailExistsException e) {
            result.addError(new FieldError("user", "email", e.message))
            return new ModelAndView("signup/signup", "user", user)
        }
        return new ModelAndView("redirect:/login")
    }
}
