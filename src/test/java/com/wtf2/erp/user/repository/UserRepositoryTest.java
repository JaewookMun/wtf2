package com.wtf2.erp.user.repository;

import com.wtf2.erp.association.domain.UserGroup;
import com.wtf2.erp.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;


    @Test
    @DisplayName("@OneToMany - @EntityGraph 기능 확인")
    void tdd() {
        //given
        String loginId = "test";

        //when
        User user = userRepository.findByLoginId(loginId).get();

        //then
        List<UserGroup> userGroups = user.getUserGroups();
        System.out.println("userGroups.size() = " + userGroups.size());
        System.out.println("group ID = " + userGroups.get(0).getGroup().getId());

        assertTrue(userGroups.size() > 0);
        assertTrue(userGroups.get(0).getGroup().getId() != null);
    }
}