package com.wtf2.erp.company.dto.api;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.util.URLEncoder;

import java.nio.charset.StandardCharsets;

@Data
public class ApiRequest {
    private String serviceKey;
    private String pageNo;
    private String numOfRows;
    private String resultType;
    private String corpNm;

    @Builder
    public ApiRequest(String serviceKey, String pageNo, String numOfRows, String resultType, String corpNm) {
        this.serviceKey = serviceKey;
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;
        this.resultType = resultType;
        this.corpNm = corpNm;
    }

    public String generateQueryString() {
        String encodedServiceKey = new URLEncoder().encode(serviceKey, StandardCharsets.UTF_8);

        return new StringBuilder()
                .append("?")
                .append("ServiceKey=").append(encodedServiceKey.replace("+", "%2B")).append("&")
                .append("pageNo=").append(pageNo).append("&")
                .append("numOfRows=").append(numOfRows).append("&")
                .append("resultType=").append(resultType).append("&")
                .append("corpNm=").append(new URLEncoder().encode(corpNm, StandardCharsets.UTF_8))
                .toString();
    }
}
