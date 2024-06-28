package com.wtf2.erp.company.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wtf2.erp.common.dto.DataTableResponse;
import com.wtf2.erp.common.dto.JsonResponse;
import com.wtf2.erp.company.domain.Company;
import com.wtf2.erp.company.dto.api.response.CompanyDto;
import com.wtf2.erp.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/search")
    public DataTableResponse<CompanyDto> search(@RequestParam(name = "name", required = false) String companyName,
                                                @RequestParam(name = "isRegistered", required = false, defaultValue = "false") Boolean isRegistered,
                                                @RequestParam(name = "draw", defaultValue = "1", required = false) int pageNo,
                                                @RequestParam(name = "length", defaultValue = "10", required = false) int size)
            throws URISyntaxException, JsonProcessingException {

        return companyService.search(isRegistered, companyName, pageNo, size);
    }

    @PostMapping(value = "/registration")
    public JsonResponse<Long> registerCompany(@RequestParam(name = "companyName") String name,
                                              @RequestParam(name = "guid") String guid) {
        log.info("companyName: {}, guid: {}", name, guid);

        return JsonResponse
                .succeed()
                .buildWith(companyService.register(name, guid));
    }

    @GetMapping(value = "/{id}")
    public Company findOne(@PathVariable("id") Long id) {
        return companyService.findBy(id);
    }


}
