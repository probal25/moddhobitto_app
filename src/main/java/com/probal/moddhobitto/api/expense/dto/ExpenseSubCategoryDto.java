package com.probal.moddhobitto.api.expense.dto;

import com.probal.moddhobitto.core.expense.model.ExpenseSubCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseSubCategoryDto {

    private Long id;

    private String name;

    private ExpenseCategoryDto parentCategory;

    private String description;

    public static ExpenseSubCategoryDto from(ExpenseSubCategory expenseSubCategory) {
        return ExpenseSubCategoryDto.builder()
                .id(expenseSubCategory.getId())
                .name(expenseSubCategory.getName())
                .parentCategory(ExpenseCategoryDto.from(expenseSubCategory.getParentExpenseCategory()))
                .description(expenseSubCategory.getDescription())
                .build();
    }

}
