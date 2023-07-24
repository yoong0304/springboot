package com.example.firstprojectretry.controller;

import com.example.firstprojectretry.dto.ArticleForm;
import com.example.firstprojectretry.entity.Article;
import com.example.firstprojectretry.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ArticleController {
//    의존성 주입(DI)
    private final ArticleRepository articleRepository;
    @Autowired
    public ArticleController(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    @GetMapping("articles/new")
    public String newArticleForm(){
        return "articles/new";
    }
    @GetMapping("articles/create")
    public String createArticleForm(ArticleForm form) {
//        1. DTO 를 Entity 로 변환
            Article article = form.toEntity();
//        2. Repository 에게 Entity 를 DB 로 저장
            Article saved = articleRepository.save(article);
        return "redirect:/articles" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public  String show(@PathVariable Long id, Model model) {
//        1. id 로 데이터를 가져옴
            Article articleEntity = articleRepository.findById(id).orElse(null);

//        2. 가져온 데이터를 모델에 등록(화면에 보여줌)
            model.addAttribute("articleEntity",articleEntity);

        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "/articles/index";
    }
    @GetMapping("/articles/edit")
    public String edit(){

        return "";
    }

}
