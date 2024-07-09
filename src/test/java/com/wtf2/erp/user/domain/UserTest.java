package com.wtf2.erp.user.domain;

import com.wtf2.erp.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void test() {
        User newUser = User.builder()
                .company(null)
                .name("james")
                .loginId("test@gmail.com")
                .password("password")
                .role(Role.SUPER_ADMIN)
                .build();

        userRepository.save(newUser);
    }
}