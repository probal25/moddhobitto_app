package com.probal.moddhobitto.api.user.dto;

import com.probal.moddhobitto.core.expense.model.UserExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserExpenseCategoryDto {

    private String expenseName;

    private String expenseCategoryName;

    public static UserExpenseCategoryDto from(UserExpenseCategory expenseCategory) {
        return UserExpenseCategoryDto.builder()
                .expenseName(expenseCategory.getName())
                .expenseCategoryName(expenseCategory.getParentExpenseCategory().getName())
                .build();
    }
}
