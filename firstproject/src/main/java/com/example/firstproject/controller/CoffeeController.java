package com.example.firstproject.controller;

import com.example.firstproject.dto.CoffeeForm;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class CoffeeController {

    @Autowired
    CoffeeRepository coffeeRepository;

//    주소값의 /articles/cnew 가 들어오면 new 생성 페이지 이동 -
//    새로운 Entity 의 내용을 입력할 페이지
    @GetMapping("/articles/cnew")
    public String newCoffeeForm() {
        return "articles/cnew";
    }

// 주소값의 articles/createcoffee 가 들어오면 새로운 Entity 생성 후 "redirect:/articles/coffee" + saved.getId()로 리턴
    @PostMapping("articles/createcoffee")
    public String createCoffeeForm(CoffeeForm form){
        Coffee coffee = form.toEntity();
        log.info(coffee.toString());

        Coffee saved = coffeeRepository.save(coffee);
        log.info(saved.toString());
        return "redirect:/articles/coffee" + saved.getId();
    }

//    주소값의 /articles/coffee{id}가 들어오면 cshow 페이지 리턴
    @GetMapping("/articles/coffee{id}")
    public String show(@PathVariable Long id, Model model){

//        orElse() - 해당 값이 null 이거나 null 이 아니어도 실행된다.
//        레포지토리에서 아이디를 찾고 그 값을 coffeeEntity 변수에 저장한다.
        Coffee coffeeEntity = coffeeRepository.findById(id).orElse(null);

//
        model.addAttribute("coffee", coffeeEntity);
        return "articles/cshow";
    }


    @GetMapping("articles/cindex")
    public String index(Model model){
//        제네릭  Coffee 타입(Long String Integer)
        List<Coffee> coffeeList = coffeeRepository.findAll();
        model.addAttribute("coffeeEntityList",coffeeList);

        return "articles/cindex";
    }



    @GetMapping("/articles/coffee{id}/cedit")
    public String coffeeEdit(@PathVariable Long id, Model model) {
        Coffee coffeeEntity = coffeeRepository.findById(id).orElse(null);
        model.addAttribute("coffeeEdit", coffeeEntity);
        return "articles/cedit";
    }
    @PostMapping("articles/coffeeupdate")
    public String update(CoffeeForm form){
        Coffee coffeeEntity = form.toEntity();

//        db에서 가져오기
        Coffee target = coffeeRepository.findById(coffeeEntity.getId()).orElse(null);

        if(target != null){
            coffeeRepository.save(coffeeEntity);
        }
        return "redirect:/articles/coffee" + coffeeEntity.getId();

    }
    @GetMapping("articles/coffee{id}/cdelete")
    public String delete(@PathVariable Long id){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);
        if(coffee!=null){
            coffeeRepository.delete(coffee);
        }
        return "redirect:/articles/cindex";
    }
}
