package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
// LOMBOK
@AllArgsConstructor     // 필드에 쓴 모든 생성자만 만들어줌
@ToString
@NoArgsConstructor      // 기본 생성자를 만들어줌
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)     // id 가 자동으로 1씩 증가
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
//      orphanRemoval 고아 객체를 true 로 지정하면 부모가 지워지면 고아객체가 된 자식 객체들도 다 삭제가 된다.
//    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Comment> comments = new ArrayList<>();

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }

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
