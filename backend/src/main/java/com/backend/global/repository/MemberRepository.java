package com.backend.global.repository;

import com.backend.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    // 중복 가입 방지
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
    Optional<Member> findById(Long id);


}
