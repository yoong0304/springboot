package hellospring.hello1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello1")
    public String hello1(Model model) {
        model.addAttribute("data", "한 번 더 hello!");
        return "hello1";
    }
    @GetMapping("spring")
    public String spring(Model model) {
        model.addAttribute("data", "진짜 졸라 어려워!");
        return "spring";
    }
}
