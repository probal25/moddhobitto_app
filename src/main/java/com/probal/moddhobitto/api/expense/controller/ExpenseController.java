package com.probal.moddhobitto.api.expense.controller;

import com.probal.moddhobitto.api.expense.dto.ExpenseCategoryDto;
import com.probal.moddhobitto.api.expense.dto.ExpenseSubCategoryDto;
import com.probal.moddhobitto.api.expense.payload.ExpenseSubCategoryPayload;
import com.probal.moddhobitto.core.expense.model.ExpenseCategory;
import com.probal.moddhobitto.core.expense.model.ExpenseSubCategory;
import com.probal.moddhobitto.core.expense.service.ExpenseService;
import com.probal.moddhobitto.core.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/categories")
    @Operation(description = "Read All Expense Categories")
    public ResponseEntity<?> getAllExpenseCategories() {

        List<ExpenseCategoryDto> categories = expenseService.getAllExpenseCategories()
                .stream()
                .map(ExpenseCategoryDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);

    }

    @GetMapping("/subcategories")
    @Operation(description = "Read All Expense Sub-Categories")
    public ResponseEntity<?> getAllExpenseSubCategories() {

        List<ExpenseSubCategoryDto> subCategories = expenseService.getAllExpenseSubCategories()
                .stream()
                .map(ExpenseSubCategoryDto::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subCategories);

    }

    @GetMapping("/category/{id}")
    @Operation(description = "Read Specific Expense Category by ID")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {

        ExpenseCategoryDto expenseCategoryResponse = ExpenseCategoryDto.from(expenseService.getExpenseCategoryById(id));
        return ResponseEntity.ok(expenseCategoryResponse);

    }

    @PostMapping("/category")
    @Operation(description = "Create new Expense Category")
    public ResponseEntity<?> addCategory(@RequestBody ExpenseCategoryDto expenseCategoryDto) {

        ExpenseCategory expenseCategory = ExpenseCategoryDto.to(expenseCategoryDto);
        expenseService.saveCategory(expenseCategory);

        return ResponseEntity.ok("Category Added");

    }

    @PostMapping("/subcategory")
    @Operation(description = "Create new Expense Sub-Category")
    public ResponseEntity<?> addSubCategory(@RequestBody @Valid ExpenseSubCategoryPayload expenseSubCategoryPayload, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorResponse.create(bindingResult, HttpStatus.BAD_REQUEST.value()));
        }
        expenseService.saveSubCategory(expenseSubCategoryPayload, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorResponse.create(bindingResult, HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.ok("Sub-Category Added");

    }

    @PutMapping("/category/{id}")
    @Operation(description = "Update Specific Expense Category by ID")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,
                                            @RequestBody ExpenseCategoryDto expenseCategoryDto) {

        ExpenseCategory existingExpenseCategory = ExpenseCategoryDto.to(expenseCategoryDto);
        expenseService.updateExpenseCategory(id, existingExpenseCategory);
        return ResponseEntity.ok("Category Updated with id -> " + id);

    }

}
