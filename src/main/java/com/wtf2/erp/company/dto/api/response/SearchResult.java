package com.wtf2.erp.company.dto.api.response;

import lombok.Data;

@Data
public class SearchResult {
    private int numOfRows;
    private int pageNo;
    private int totalCount;
    private Items items;
}
