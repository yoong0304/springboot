package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Slf4j
public class FirstApiController {

    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/api/hello")
    public String hello(){
        return "hello world";
    }

    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

//    Post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
//    @RequestBody 는 http 요청의 본문(body)에 들어 있는 데이터를 자바객체로 매핑하는 데 사용됩니다.
//    컨트롤러 메서드의 파라미터에 적용하면, 해당 메서드는 HTTP 요청의 본문에 담긴 데이터를 해당 파라미터의 객체로 변환하여 사용한다.

//    PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
//        1. dto -> 엔티티
            Article article = dto.toEntity();
            log.info("id : {}, articles : {}", id, article.toString());
//        2. 타겟을 조회
            Article target = articleRepository.findById(id).orElse(null);
//        3. 잘못된 요청 처리
            if(target == null || id != article.getId()){
//                400 error, 잘못된 요청 응답
                log.info("잘못된 요청! id : {}, articles : {}", id, article.toString());
//                return ResponseEntity.status(400).body(null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 에러 코드 400을 던지고 body 에는 아무것도 넣지 않겠다.
            }

            target.patch(article);
            Article updated = articleRepository.save(target);
            return ResponseEntity.status(HttpStatus.OK).body(updated);  // 정상 작동
    }

//    Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
//        대상 찾기
            Article target = articleRepository.findById(id).orElse(null);
//        잘못된 요청
            if(target == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
//        대상 삭제
            articleRepository.delete(target);
            return ResponseEntity.status(HttpStatus.OK).build();
//          build() 메서드는 응답 본문이 필요하지 않을 때 사용합니다.
//          상태코드와 헤더만으로 응답을 완성해야 하는 경우에 유용합니다.
    }
}
