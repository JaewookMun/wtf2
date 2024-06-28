package com.wtf2.erp.company.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;

@SpringBootTest
class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Test
    void test() throws URISyntaxException, JsonProcessingException {
        companyService.search(false,"삼성전자", 1, 10);
    }

}