package com.example.firstprojectretry.dto;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.firstprojectretry.entity.Article;

public class ArticleForm {


=======
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1

import com.example.firstprojectretry.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
    private String title;
    private String content;

//    생성자
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

//    toString()
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity(){
        return new Article(null, title, content);
    }

=======
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
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
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
}
