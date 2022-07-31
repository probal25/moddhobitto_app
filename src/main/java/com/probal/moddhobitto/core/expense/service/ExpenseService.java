package com.probal.moddhobitto.core.expense.service;



import com.probal.moddhobitto.core.expense.model.ExpenseCategory;
import com.probal.moddhobitto.core.expense.repository.ExpenseCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseCategoryRepository expenseCategoryRepository;

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
}
