package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
//    mustache
    @GetMapping("/hello")
    public String niceToMeetYou() {
        return "hello";
    }
    @GetMapping("/hi")
    public String greetings(Model model){
        model.addAttribute("username","junsuk");
        return "greetings";
    }
    @GetMapping("/bye")
    public String goodbye(Model model) {
        model.addAttribute("a", "홍길동");
        return "goodbye";

    }

//    thymeleaf
    @GetMapping("hie")
    public String hello(Model model) {
        model.addAttribute("data","졀라 어려워요");
        return "hello.html";
    }


}
