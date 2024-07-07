package com.wtf2.erp.common.dto;

import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Getter @Setter
@ToString
@AllArgsConstructor
@Builder
public class DataTableRequest {
    /** pageNo */
    @Min(1)
    private int draw;
    /** size */
    private int length;
}
