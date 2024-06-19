package com.wtf2.erp.company.dto.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Items {
    @JsonProperty("item")
    private List<CompanyDto> companyList;
}
