package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager em;

//    ↓ 순수 JPA
    public Long save(Member member){
        em.persist(member);     // 저장
        return member.getId();
    }
    public Member find(Long id){
        return em.find(Member.class, id);   // 찾기  class를 꼭 써줘야 된다.
    }
}
//


