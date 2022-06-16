package com.probal.moddhobitto.core.expense.service;


import com.probal.moddhobitto.api.expense.dto.ExpenseCategoryDto;
import com.probal.moddhobitto.api.expense.payload.ExpenseSubCategoryPayload;
import com.probal.moddhobitto.core.expense.model.ExpenseCategory;
import com.probal.moddhobitto.core.expense.model.ExpenseSubCategory;
import com.probal.moddhobitto.core.expense.repository.ExpenseCategoryRepository;
import com.probal.moddhobitto.core.expense.repository.ExpenseSubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    private final ExpenseSubCategoryRepository expenseSubCategoryRepository;

    public List<ExpenseCategory> getAllExpenseCategories() {

        return expenseCategoryRepository.findAll();
    }

    public ExpenseCategory getExpenseCategoryById(Long id) {

        return expenseCategoryRepository.findById(id).orElse(null);
    }

    public void saveCategory(ExpenseCategory expenseCategory) {
        expenseCategoryRepository.save(expenseCategory);
    }

    public void updateExpenseCategory(Long id, ExpenseCategory existingExpenseCategory) {
        ExpenseCategory expenseCategory = expenseCategoryRepository.findById(id).orElse(null);
        if (expenseCategory != null) {
            expenseCategory.setName(existingExpenseCategory.getName());
            expenseCategory.setDescription(existingExpenseCategory.getDescription());
            expenseCategory.setUpdatedAt(new Date());
            expenseCategoryRepository.save(expenseCategory);
        }
    }

    public void saveSubCategory(ExpenseSubCategoryPayload expenseSubCategoryPayload, BindingResult bindingResult) {

        ExpenseSubCategory expenseSubCategory = new ExpenseSubCategory();

        expenseSubCategory.setName(expenseSubCategoryPayload.getName());
        expenseSubCategory.setDescription(expenseSubCategoryPayload.getDescription());
        ExpenseCategory expenseCategory = getExpenseCategoryById(expenseSubCategoryPayload.getParentCategoryId());
        if (expenseCategory == null) {
            FieldError error = new FieldError("expenseSubCategoryPayload", "parentCategoryId", "Enter valid parent category id");
            bindingResult.addError(error);
            return;
        }
        expenseSubCategory.setParentExpenseCategory(expenseCategory);
        expenseSubCategory.setActive(true);

        expenseSubCategoryRepository.save(expenseSubCategory);
    }

    public List<ExpenseSubCategory> getAllExpenseSubCategories() {
        return expenseSubCategoryRepository.findAll();
    }
}
