package com.example.firstprojectretry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class FirstController {
//    mustache
    @GetMapping("hello")
    public String niceTOMeetYou(){
        return "hello";
    }
    @GetMapping("hi")
    public String greetings(Model model) {
        model.addAttribute("username", "JAEWOONG");
        return "greetings";
    }
    @GetMapping("bye")
    public String goodBye(Model model){
        model.addAttribute("a", "byebye~");
        model.addAttribute("username", "JAEWOONG");
        return "goodbye";
    }



//    thymeleaf
    @GetMapping("thyhello")
    public  String hello(Model model){
        model.addAttribute("data","졸라 어려워..");
        return "hello";
    }
}
