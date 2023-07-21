package hellospring.hello1.service;

import hellospring.hello1.domain.Member;
import hellospring.hello1.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService = new MemberService(memberRepository);

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    @Test
    void join() {
//        given
        Member member = new Member();
        member.setName("Spring");
//        when
        Long saveId = memberService.join(member);
//        than
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

        System.out.println("member : " + member.getName());
        System.out.println("findMember : " + findMember.getName());
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