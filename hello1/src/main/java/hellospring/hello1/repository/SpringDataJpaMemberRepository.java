package hellospring.hello1.repository;

import hellospring.hello1.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends CrudRepository<Member, Long>, MemberRepository {     // extends -> 다중 상속
    @Override
    Optional<Member> findByName(String name);
}

//스프링 데이터 JPA
//스프링 데이터 JPA 를 사용하면, 레포지토리에 구현 클래스 없이 인터페이스만으로 개발을 완료할 수 있다.
//그리고 반복 개발해온 기본 CRUD 기능도 스프링 데이터 JPA 가 모두 제공한다.

//  - 스프링 데이터 JPA 제공 기능 -
//    인터페이스를 통한 기본적인 CURD
//    findByName(), findByEmail() 처럼 메소드 이름만으로 조회 기능 제공
//    페이징 기능 자동 제공


