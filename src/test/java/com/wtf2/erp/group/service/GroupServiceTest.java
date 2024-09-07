package com.wtf2.erp.group.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wtf2.erp.common.dto.DataTableRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;

@SpringBootTest
class GroupServiceTest {

    @Autowired
    GroupService groupService;

    @Test
    void test() {
        groupService.search("삼성전자");
    }

}