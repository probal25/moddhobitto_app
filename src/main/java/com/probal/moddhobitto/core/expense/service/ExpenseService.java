package com.probal.moddhobitto.core.expense.service;


import com.probal.moddhobitto.api.expense.dto.ExpenseCategoryDto;
import com.probal.moddhobitto.core.expense.model.ExpenseCategory;
import com.probal.moddhobitto.core.expense.repository.ExpenseCategoryRepository;
import com.probal.moddhobitto.core.expense.repository.ExpenseSubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
