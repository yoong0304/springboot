package thymeleaf.thymeleafex.controller;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping(value = "/basic")   // 기본적으로 url 에 basic 이 붙게한다.
public class BasicController {

    @GetMapping(value = "text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello <b>Spring</b>");
        return "basic/text-basic";
    }

    @GetMapping(value = "/variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>(Arrays.asList(userA,userB));

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping(value = "/basic-objects")
    public String basicObject(HttpSession httpSession){
        httpSession.setAttribute("sessionData", "Hello Session");
        return "basic/basic-object";
    }

    @Component(value = "helloBean")
    static class HelloBean{
        public String hello(String data){
            return "Hello " + data;
        }
    }

    @GetMapping(value = "/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping(value = "literal")
    public String literal(Model model){
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping(value = "/operation")
    public String operation(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    @GetMapping(value = "/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }

    private void addUsers(Model model){
        List<User> users = Arrays.asList(new User("userA", 10),
                new User("userB", 20),
                new User("userC", 30));

        model.addAttribute("users", users);
    }

    @GetMapping(value = "/condition")
    public String condition(Model model) {
        addUsers1(model);
        return "basic/condition";
    }
//    위에서 작성했던 메서드니 중복에 유의하자
    private void addUsers1(Model model){
        List<User> users = Arrays.asList(new User("userA", 10),
                new User("userB", 20),
                new User("userC", 30));
        model.addAttribute("users", users);
    }

    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age){
            this.username = username;
            this.age = age;
        }
    }
}
