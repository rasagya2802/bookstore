package com.example.bookstore.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about"; 
    }
}
