package com.zucareli.security.authorization.configuration.web

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfigurer implements WebMvcConfigurer
{
    @Override
    void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("login").setViewName("signin/signin")
        registry.addViewController("/").setViewName("index")
    }
}
