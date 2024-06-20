package com.wtf2.erp.company.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wtf2.erp.company.dto.api.ApiRequest;
import com.wtf2.erp.company.dto.api.ApiResponse;
import com.wtf2.erp.company.dto.api.response.SearchResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CompanyApiService {

    private final RestTemplate restTemplate;

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
    public SearchResult search(String name, int pageNo) throws JsonProcessingException, URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        List<MediaType> accept = new ArrayList<>();
        accept.addAll(headers.getAccept());
        accept.add(MediaType.APPLICATION_XML);
        accept.add(MediaType.TEXT_XML);
        headers.setAccept(accept);

        ApiRequest requestForm = ApiRequest.builder()
                .serviceKey(authorizedKey)
                .pageNo(String.valueOf(pageNo))
                .numOfRows(String.valueOf(10))
                .corpNm(name)
                .resultType("json")
                .build();

        log.debug("[URL]: {}", searchUrl + requestForm.generateQueryString());
        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(new URI(searchUrl + requestForm.generateQueryString()), ApiResponse.class);

        log.debug(response);
        if(response.getStatusCode().is2xxSuccessful())
            return response.getBody().getResponse().getSearchResult();

        throw new HttpServerErrorException(response.getStatusCode());
    }
}
