package com.backend.domain.refreshtoken.repository;

import com.backend.domain.refreshtoken.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    // member id 값으로 refresh token 찾기
    Optional<RefreshToken> findByKey(Long key);
}
