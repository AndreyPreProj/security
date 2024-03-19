package ru.kata.spring.boot_security.demo.controllers;

import org.hibernate.Hibernate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.security.PersonDetails;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TestController {

    @GetMapping("/hello")
    public String hi() {
        return "test";
    }

    @GetMapping("/showUser")
    @Transactional
    public String show() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) auth.getPrincipal();
        System.out.println(personDetails.getPerson());

        List<String> roles = personDetails.getPerson().getRoles();


        if (roles != null) {
            for (String s : roles) {
                System.out.println(s);
            }
        }
        return "testik";
    }

    @GetMapping("/test")
    public String admin() {
        return "adminPage";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }
}
