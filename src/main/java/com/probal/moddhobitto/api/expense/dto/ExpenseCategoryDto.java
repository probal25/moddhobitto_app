package com.probal.moddhobitto.api.expense.dto;

import com.probal.moddhobitto.core.expense.model.ExpenseCategory;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

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

    public static ExpenseCategory to(ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setName(expenseCategory.getName());
        expenseCategory.setDescription(expenseCategoryDto.getDescription());
        expenseCategory.setActive(true);
        expenseCategory.setCreatedAt(new Date());

        return expenseCategory;
    }
}
