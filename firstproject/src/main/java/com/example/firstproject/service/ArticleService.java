package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

//    create
    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }   // 기존 데이터 수정 방지
        return articleRepository.save(article);
    }

//    update
    public Article update(Long id, ArticleForm dto) {
//        1. dto -> Entity
        Article article = dto.toEntity();
        log.info("id : {}, articles : {}", id, article.toString());

//        2. target 조회
        Article target = articleRepository.findById(id).orElse(null);

//        3. 잘못된 요청 처리
        if (target == null || id != article.getId() ){
//            400 error, 잘못된 요청 응답
            log.info("잘못된 요청! id : {}, articles : {}", id, article.toString());
            return null;
        }
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }


    public Article delete(Long id) {
//        대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
//        잘못된 요청 처리
        if(target == null){
            return null;
        }
//        대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticle(List<ArticleForm> dtos) {
//        List<Article> articleList = new ArrayList<>();
//        for(int i = 0; i < dtos.size(); i++){
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articleList.add(entity);
//        }
//        ↓ stream
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
//        entity 리스트로 만들기 위해 map 을 통해 스트림화 하여
//        dto 가 올 때마다 entity 로 맵핑 된 것을 리스트로 변환

//        for(int i = 0; i < articleList.size(); i++){
//            Article article = articleList.get(i);
//            articleRepository.save(article);
//        }
//        ↓ stream
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

//        강제 예외를 발생
            articleRepository.findById(-1L).orElseThrow(
                    () -> new IllegalArgumentException("결제 실패;")
            );

//        결과값 반환
        return articleList;
    }
}
