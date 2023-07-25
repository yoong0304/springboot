package com.example.firstprojectretry.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor // 기본 생성자 lombok
@AllArgsConstructor // 필드 생성자
public class Article {
//    필드
    @Id
    @GeneratedValue     // id 를 자동으로 1씩 증가 시켜준다
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

//    생성자
//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

//    toString()
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
