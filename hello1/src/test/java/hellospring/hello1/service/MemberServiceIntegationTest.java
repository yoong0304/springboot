package hellospring.hello1.service;

import hellospring.hello1.domain.Member;
import hellospring.hello1.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemoryMemberRepository memberRepository;



    @Test
    void 회원가입() throws Exception{
//        given
        Member member = new Member();
        member.setName("Spring");
//        when
        Long saveId = memberService.join(member);
//        than
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void 중복회원인경우() {
        Member member1 = new Member();
        member1.setName("Spring1");

        Member member2 = new Member();
        member2.setName("Spring1");
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회언");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}