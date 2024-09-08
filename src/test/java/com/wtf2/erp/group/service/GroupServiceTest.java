package com.wtf2.erp.group.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GroupServiceTest {

    @Autowired
    GroupService groupService;

    @Test
    @DisplayName("그룹 등록")
    void test() {
        // given
        String groupName = "WTF2 - TEST";
        String loginId = "test";

        // when
        groupService.register(groupName, loginId);

        
    }


}