package com.example.firstprojectretry.repository;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.firstprojectretry.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
=======
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1

import com.example.firstprojectretry.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Override
    ArrayList<Article> findAll();
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
}
