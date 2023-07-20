package hellospring.hello1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello1")
    public String hello1(Model model) {
        model.addAttribute("data", "한 번 더 hello!");
        return "hello1";
    }
//    @GetMapping("hello1") hello1 경로로 들어오는 get 요청
//    hello1(Model model) 뷰(html)에 데이터 전달
//    "data"라는 이름으로 "한 번 더 hello!"라는 값을 모델에 추가


    @GetMapping("spring")
    public String spring(Model model) {
        model.addAttribute("data", "진짜 졸라 어려워!");
        return "spring";
    }
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name1, Model model) {
        model.addAttribute("name", name1);
        return "hello-template";
    }
//    http://localhost:8080/hello-mvc?name=babo
//    @RequestParam("name")은 클라이언트가 요청 파라미터로 전달

    @GetMapping("hello-String")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }
//    http://localhost:8080/hello-string?name=spring!!!!
//    @ResponseBody -> http 에서 바디부분에 직접 내용을 넣어주겠다는 의미


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
