package com.wtf2.erp.company.dto.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Response {
    private Header header;
    @JsonProperty("body")
    private SearchResult searchResult;
}
