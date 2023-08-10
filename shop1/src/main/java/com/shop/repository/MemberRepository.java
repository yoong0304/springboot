package com.shop.repository;

import com.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/* JpaRepository 상속 받음 */
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
