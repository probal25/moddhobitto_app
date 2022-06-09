package com.probal.moddhobitto.api.expense.dto;

import com.probal.moddhobitto.core.expense.model.ExpenseCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseCategoryDto {

    private Long id;

    private String name;

    private String description;

    public static ExpenseCategoryDto from(ExpenseCategory expenseCategory) {
        return ExpenseCategoryDto.builder()
                .id(expenseCategory.getId())
                .name(expenseCategory.getName())
                .description(expenseCategory.getDescription())
                .build();
    }

}
