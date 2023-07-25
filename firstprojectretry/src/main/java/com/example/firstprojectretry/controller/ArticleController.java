package com.example.firstprojectretry.controller;

import com.example.firstprojectretry.dto.ArticleForm;
import com.example.firstprojectretry.entity.Article;
import com.example.firstprojectretry.repository.ArticleRepository;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
//    의존성 주입(DI)
//    ↓ 기본적인 DI
//    private final ArticleRepository articleRepository;
//    @Autowired
//    public ArticleController(ArticleRepository articleRepository){
//        this.articleRepository = articleRepository;
//    }
//    ↓ 위험한 DI
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("articles/create")
    public String createArticleForm(ArticleForm form){
        System.out.println(form.toString());

//        1. DTO 를 Entity 로 변환
            Article article = form.toEntity();
            System.out.println(article.toString());
//        2. Repository 에게 Entity 를 DB로 저장
//          여기서 saved 는 CRUD 안에 내제되어있다.
            Article saved = articleRepository.save(article);
            System.out.println(saved.toString());
        return "";
    }
=======
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
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
        return "redirect:/articles/" + saved.getId();
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
        return "articles/index";
    }
    @GetMapping("articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
//        수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

//        모델 데이터 등록
        model.addAttribute("articleEdit",articleEntity);

//        뷰페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
//        Entity 로 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
//        DB 저장
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target != null){
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id){

        Article articleEntity = articleRepository.findById(id).orElse(null);

        if (articleEntity != null){
            articleRepository.delete(articleEntity);
        }
        return "redirect:/articles";
    }

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
}
