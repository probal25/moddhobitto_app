package com.probal.moddhobitto.core.expense.repository;

import com.probal.moddhobitto.core.expense.model.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
}
