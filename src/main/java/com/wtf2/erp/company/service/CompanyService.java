package com.wtf2.erp.company.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wtf2.erp.common.dto.DataTableResponse;
import com.wtf2.erp.company.domain.Company;
import com.wtf2.erp.company.dto.api.ApiRequest;
import com.wtf2.erp.company.dto.api.ApiResponse;
import com.wtf2.erp.company.dto.api.response.CompanyDto;
import com.wtf2.erp.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final RestTemplate restTemplate;
    private final CompanyRepository companyRepository;

    @Value("${public.data.encoding-key}")
    private String authorizedKey;

    @Value("${public.data.search-url}")
    private String searchUrl;

    /**
     * 
     * @param name
     * @param pageNo 1부터 시작
     * @return
     * @throws JsonProcessingException
     * @throws URISyntaxException
     */
    public DataTableResponse<CompanyDto> search(Boolean isRegistered, String name, int pageNo, int size) throws JsonProcessingException, URISyntaxException {

        if (isRegistered) {
            // TODO: 현재는 SELECT로 전체를 가져온 후 stream에서 limit() 을 통해 자르지만 쿼리에서 하도록 수정
            List<CompanyDto> list = companyRepository.findByName(name).stream()
                    .map(c -> new CompanyDto(c.getName(), c.getGuid()))
                    .collect(Collectors.toList());

            int totalCnt = list.size();

            return DataTableResponse.<CompanyDto>builder()
                    .data(list.stream().limit(size).collect(Collectors.toList()))
                    .draw(pageNo)
                    .recordsTotal(totalCnt)
                    .recordsFiltered(totalCnt)
                    .build();
        }

        ApiRequest requestForm = ApiRequest.builder()
                .serviceKey(authorizedKey)
                .pageNo(String.valueOf(pageNo))
                .numOfRows(String.valueOf(size))
                .corpNm(name)
                .resultType("json")
                .build();

        log.debug("[URL]: {}", searchUrl + requestForm.generateQueryString());
        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(new URI(searchUrl + requestForm.generateQueryString()), ApiResponse.class);

        log.debug(response);
        if(response.getStatusCode().is2xxSuccessful())
            return response.getBody().getResponse().getSearchResult().convertToTableResponse();

        throw new HttpServerErrorException(response.getStatusCode());
    }

    public Long register(String name, String guid) {
        companyRepository.findByGuid(guid).ifPresent(c -> {
            throw new DataIntegrityViolationException("GUID is duplicate - guid is Unique key constraint");
        });

        return companyRepository.save(new Company(name, guid)).getId();
    }

    public Company findBy(Long id) {
        return companyRepository.findById(id).orElseThrow();
    }
}
