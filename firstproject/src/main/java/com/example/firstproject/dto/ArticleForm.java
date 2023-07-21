package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor //lombok
@ToString   //lombok
public class ArticleForm {
    private String title;
    private String content;
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity() {
        return new Article(null, title, content);
    }
//    toEntity() 메소드는 새로운 Article 을 생성하는데
//    기본인 id 는 null 로 설정하고, title 과 content 는 DTO(ArticleForm) 에서 전달된 값으로 설정




//    DTO - 데이터 전송객체
//    계층들 사이에서 데이터를 간단하고 구조화된 방식으로 캡슐화하고 전송하는 것
}
