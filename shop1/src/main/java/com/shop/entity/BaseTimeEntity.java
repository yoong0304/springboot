package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class}) // 생성일과 수정일 자동관리
@MappedSuperclass // 다른 엔티티 클래스에서 상속 받아서 사용 할 수 있다
@Getter @Setter
public abstract class BaseTimeEntity {
    @CreatedDate // 엔티티가 생성될때 해당 필드에 자동으로 생성일 저장
    @Column(updatable = false) // 업데이트되지 않도록 만듬
    private LocalDateTime regTime; // 등록 시간
    @LastModifiedDate // JPA 의 Auditing 기능으로, 엔티티가 수정될 떄 해당 필드에 자동으로 수정일을 사용
    private LocalDateTime updateTime; // 수정 시간
}