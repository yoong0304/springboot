package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor    // final 이 붙은 애를 생성자 방식으로 DI 하는 방법
// final 필드가 붙은 필드를 인자를 받는 생성자가 자동으로 생성
public class MemberRepository {
    @PersistenceContext
    private final EntityManager em;

//    ↓ 순수 JPA
    public void save(Member member){
        em.persist(member);     // 저장
    }
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

/*
    public List<Member> findAll(){
        TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
        List<Member> result = query.getResultList();
        return result;
    }
*/
//    간축형   위에와 동일
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }


/*    public List<Member> findByName(String name){
//        JPQL :name
        TypedQuery<Member> query = em.createQuery("select m from Member where m.name = :name", Member.class);
//        쿼리에 바인딩 변수에 값을 할당합니다
        query.setParameter("name",name);    // 이걸 써줘야 적정한 name 값을 찾아준다
        List<Member> result = query.getResultList();
        return result;
    }
*/
//    간축형   위에와 동일
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }


}
//


