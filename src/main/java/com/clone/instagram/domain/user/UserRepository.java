package com.clone.instagram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 소셜 로그인으로 반환되는 값 중 email 을 통해 사용자의 사용 유무를 판단하기 위한 메소드
    Optional<User> findByEmail(String email);
    User getByEmail(String email);
}
