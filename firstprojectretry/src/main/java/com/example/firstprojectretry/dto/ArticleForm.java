package com.example.firstprojectretry.dto;


import com.example.firstprojectretry.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

//    생성자
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

//    toString()
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
