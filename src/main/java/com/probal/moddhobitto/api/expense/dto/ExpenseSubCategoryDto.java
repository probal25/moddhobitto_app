package com.probal.moddhobitto.api.expense.dto;

import com.probal.moddhobitto.core.expense.model.UserExpenseCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseSubCategoryDto {

    private Long id;

    private String name;

    private ExpenseCategoryDto parentCategory;

    private String description;

    public static ExpenseSubCategoryDto from(UserExpenseCategory userExpenseCategory) {
        return ExpenseSubCategoryDto.builder()
                .id(userExpenseCategory.getId())
                .name(userExpenseCategory.getName())
                .parentCategory(ExpenseCategoryDto.from(userExpenseCategory.getParentExpenseCategory()))
                .description(userExpenseCategory.getDescription())
                .build();
    }

}
