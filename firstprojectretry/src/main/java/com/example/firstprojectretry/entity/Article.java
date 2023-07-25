package com.example.firstprojectretry.entity;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
public class Article {
    @Id
    @GeneratedValue     // id 를 자동으로 1씩 증가 시켜줌
    private Long id;
    @Column             // 잡다한 것들은 다 Column
=======
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
@Getter
@NoArgsConstructor // 기본 생성자 lombok
@AllArgsConstructor // 필드 생성자
public class Article {
//    필드
    @Id
    @GeneratedValue     // id 를 자동으로 1씩 증가 시켜준다
    private Long id;
    @Column
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
    private String title;
    @Column
    private String content;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
//    생성자(constructor)
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
=======
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
//    생성자
//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1
=======
>>>>>>> 47ae92ca21475c5ce0e0db900b8d8022697813f1

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
