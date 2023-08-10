package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional  /* service 는 기본적으로 써줘야 함 */
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
//    DI
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null ) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null){
            throw new UsernameNotFoundException(email);
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString()) /* -> toString() 하면 ROLE_USER or ROLE_ADMIN 으로 출력된다 */
                .build();

//        UserDetails 객체는 Spring Security 에서 사용자의 인증 및 권한 정보를 나타내는 인터페이스
//        loadUserByUsername 메서드는 이 정보를 검색하여 해당 정보를 기반으로 사용자 인증 및 권한 부여를 수행하기 위해 사용
//        이 메서드를 구현하여 사용자 정보를 데이터 베이스에 가져와 Spring Security 의 사용자 인증 및 권한 기능을 활용
    }
}
