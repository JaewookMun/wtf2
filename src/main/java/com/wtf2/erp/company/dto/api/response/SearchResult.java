package com.wtf2.erp.company.dto.api.response;

import com.wtf2.erp.common.dto.DataTableResponse;
import lombok.Data;

@Data
public class SearchResult {
    private int numOfRows;
    private int pageNo;
    private int totalCount;
    private Items items;

    public DataTableResponse<CompanyDto> convertToTableResponse() {
        return DataTableResponse.<CompanyDto>builder()
                .data(items.getCompanyList())
                .draw(pageNo)
                .recordsFiltered(totalCount)
                .recordsTotal(totalCount)
                .build();
    }
}
