package com.wtf2.erp.user.repository;

import com.wtf2.erp.user.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "company")
    Optional<User> findByLoginId(String loginId);
}

