package com.wtf2.erp.company.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wtf2.erp.company.domain.Company;
import com.wtf2.erp.company.dto.api.response.SearchResult;
import com.wtf2.erp.company.service.CompanyApiService;
import com.wtf2.erp.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/company")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyApiService companyApiService;

    @GetMapping("/search")
    public SearchResult search(@RequestParam(name = "name", required = false) String companyName,
                               @RequestParam(name = "pageNo", defaultValue = "1", required = false) int pageNo)
            throws URISyntaxException, JsonProcessingException {

        return companyApiService.search(companyName, pageNo);
    }

    @PostMapping(value = "/register")
    public String registerCompany(String name, String crno) {
        companyService.register(name, crno);

        return "ok";
    }

    @GetMapping(value = "/{id}")
    public Company findOne(@PathVariable("id") Long id) {
        return companyService.findBy(id);
    }


}
