package com.yana.internship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {// TODO: 2019-10-29 think about better name

   @GetMapping("/")
   public String crud() {
      return "crud";
   }
}
