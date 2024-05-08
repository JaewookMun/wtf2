package com.wtf2.erp.user.repository;

import com.wtf2.erp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
