package com.probal.moddhobitto.api.user.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class UserBalanceSheetDto {

    private String categoryName;

    private String parentCategoryName;

    private BigDecimal amount;
}
