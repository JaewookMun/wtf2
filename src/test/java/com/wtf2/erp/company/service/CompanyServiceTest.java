package com.wtf2.erp.company.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wtf2.erp.common.dto.DataTableRequest;
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

        companyService.search(false
                ,"삼성전자"
                , DataTableRequest.builder()
                        .draw(1).length(10).build());
    }

}