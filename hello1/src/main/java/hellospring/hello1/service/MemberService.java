package hellospring.hello1.service;

import hellospring.hello1.domain.Member;
import hellospring.hello1.repository.MemberRepository;
import hellospring.hello1.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    //    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }   // DI 생성자 방식


//    service 를 만들기 위해서는 repository 가 있어야 한다.
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

//    @Autowired
//    private MemberRepository memberRepository;

//    private MemberRepository memberRepository;
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


    // 회원 가입
    public long join(Member member) {

//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {           // 조건
//            throw new IllegalStateException("이미 존재하는 회원");
//        });
        // 같은 이름이 있으면 회원가입이 안되게 하기 -> 프로젝트 조건

        // ifPresent -> null 이 아니라 어떤 값이 있으면
        // 혹시나 null 일 수 있으면 Optional 로 감싸서 반환
        // 추가적으로 result.orElseGet(값이 있으면 꺼내고 없으면 여기 있는 메소드 실행)를 많이 사용한다.

        // 메서드 추출 ctrl + alt + m 안되면 전구 클릭 -> extract method 를 클릭해서 추출

        validateDuplicateMember(member);    // 중복된 회원 검증   메서드 추출
        // 회원가입시 아이디를 반환시켜준다.
        memberRepository.save(member);
        return member.getId();
    }

    // 중복회원검증    메서드 추출(ctrl+alt+m)
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
//        시간 체크
//        long start = System.currentTimeMillis();
//        try{
//            return memberRepository.findAll();
//        }finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("findMembers " + timeMs + "ms");
//        }

    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
