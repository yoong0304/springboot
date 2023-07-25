package com.example.firstprojectretry.api;

import com.example.firstprojectretry.dto.ArticleForm;
import com.example.firstprojectretry.entity.Article;
import com.example.firstprojectretry.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class FirstApiController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/api/hello")
    public String hello(){
        return "hello world!";
    }
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

//    POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
}
