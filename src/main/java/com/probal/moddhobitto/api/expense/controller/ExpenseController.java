package com.probal.moddhobitto.api.expense.controller;

import com.probal.moddhobitto.api.expense.dto.ExpenseCategoryDto;
import com.probal.moddhobitto.core.expense.model.ExpenseCategory;
import com.probal.moddhobitto.core.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/categories")
    public ResponseEntity<?> getAllExpenseCategories() {

        List<ExpenseCategoryDto> categories = expenseService.getAllExpenseCategories()
                .stream()
                .map(ExpenseCategoryDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);

    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {

        ExpenseCategoryDto expenseCategory = ExpenseCategoryDto.from(expenseService.getExpenseCategoryById(id));
        return ResponseEntity.ok(expenseCategory);

    }

}
