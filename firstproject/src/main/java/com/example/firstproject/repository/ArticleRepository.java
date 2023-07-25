package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
//    Crud 내에 Iterable 로 선언 되어 있어서 List 로 쓸라면 오버라이딩 해야 된다.
//    List 는 ArrayList 의 부모로 ArrayList 로 선언 하든, List 로 선언하든 상관없다.
}
