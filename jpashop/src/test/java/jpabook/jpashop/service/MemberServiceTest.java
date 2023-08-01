package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)  // SpringExtension.class JUnit5 부터 먹음
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    void 회원가입() {
//        Given - 테스트를 준비하기 위한 초기 상태
//            테스트를 실행하기 위해 필요한 데이터나 객체등 준비하는 작업
        Member member = new Member();
        member.setName("Kim");

//        When - 실제 테스트한 동작수행
//            테스트하고자 하는 특정 동작이나 메서드 호출 등을 실행
        Long saveId = memberService.join(member);
        Member expect = memberRepository.findOne(saveId);

//        Then - 테스트 결과 확인
//            예상결과와 실제 결과를 비교하여 테스트의 성공 여부 판단.
//        assertEquals(member, memberRepository.findOne(saveId));
        assertEquals(member.toString(), expect.toString());
        System.out.println(member.toString());
        System.out.println(expect.toString());

    }

//    (expected = IllegalStateException.class)
    @Test
    public void 중복_회원_예외() throws Exception {
//        given
        Member member1 = new Member();
        member1.setName("Kim");
        Member member2 = new Member();
        member2.setName("Kim");

//        When
        memberService.join(member1);
//        memberService.join(member2);    // 예외가 발생해야 한다.

//        Then
//        fail("예외가 발생해야 한다.");
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
//    위에서는 IllegalArgumentException 이 더 적절 할 거 같다.
//    IllegalArgumentException - 메서드에 전달된 인자가 유효하지 않은 경우 발생하는 예외
//    IllegalStateException - 메서드 호출이 잘못된 상태에서 수행될 때 발생하는 예외
//    DuplicateKeyException - 데이터 베이스나 저장속에 중복된 키로 데이터를 삽입하고자 할 때

    @Test
    void findOne() {
    }
}