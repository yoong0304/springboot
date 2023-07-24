package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
// LOMBOK
@AllArgsConstructor     // 필드에 쓴 모든 생성자만 만들어줌
@ToString
@NoArgsConstructor      // 기본 생성자를 만들어줌
@Data
public class Article {
    @Id
    @GeneratedValue     // id 가 자동으로 1씩 증가
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
//
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
