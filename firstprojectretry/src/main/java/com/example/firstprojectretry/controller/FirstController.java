package com.example.firstprojectretry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class FirstController {
    @GetMapping("hello")
    public  String hello(Model model){
        model.addAttribute("data","졸라 어려워..");
        return "hello";
    }
}
