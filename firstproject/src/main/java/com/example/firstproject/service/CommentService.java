package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Transactional      // create, update, delete 에 하나씩 다 붙이거나 클래스 전체에 붙이고 comments 에 readOnly 붙이기
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<CommentDto> comments(Long articleId) {
//        조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);

//        변환 엔티티 -> DTO
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i = 0; i < comments.size(); i++){
//            Comment c= comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
//        ↓ stream() 으로
//        List<CommentDto> dtos = comments.stream()
//                .map(CommentDto::createCommentDto)
//                .collect(Collectors.toList());

//        반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    public CommentDto create(Long articleId, CommentDto dto) {
//        게시글 조회 및 예외발생
        Article article = articleRepository.findById(articleId).orElseThrow(
                () -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다.")
        );
//        댓글 생성
        Comment comment = Comment.createComment(dto, article);

//        댓글 엔티티를 DB 로 저장
        Comment created = commentRepository.save(comment);

//        DTO 로 변경하여 반환
        return CommentDto.createCommentDto(created);
    }

    public CommentDto update(Long id, CommentDto dto) {
//        댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("댓글 수정 실패"));
//        댓글 수정
        target.patch(dto);
//        DB 로 갱신
        Comment updated = commentRepository.save(target);
//        댓글 엔티티를 dto로 변환
        return CommentDto.createCommentDto(updated);
    }


    public CommentDto delete(Long id) {
//        댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패"));

//        댓글 삭제
        commentRepository.delete(target);
        return CommentDto.createCommentDto(target);

    }

}
