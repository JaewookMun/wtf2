package com.wtf2.erp.company.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyApiServiceTest {

    @Autowired
    CompanyApiService companyApiService;

    @Test
    void test() throws URISyntaxException, JsonProcessingException {
        companyApiService.search("삼성전자", 1);
    }

}