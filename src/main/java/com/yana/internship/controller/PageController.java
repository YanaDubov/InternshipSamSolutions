package com.yana.internship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping("/item")
    public String crud() {
        return "booking";
    } // TODO: 2019-12-18 booking

    @GetMapping("/login")
    public String login() {
        return "login";
    }



}
