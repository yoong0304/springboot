package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 저장하는 것만 뭐뭐머고 나머지는 readOnly = true
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

//    회원가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);    // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {   // findMembers 가 비어있지 않다면
            throw new IllegalStateException("이미 존재하는 회원입니다.");  // 예외처리
        }
    }

//    회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
