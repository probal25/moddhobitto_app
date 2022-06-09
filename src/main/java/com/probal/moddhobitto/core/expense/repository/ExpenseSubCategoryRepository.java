package com.probal.moddhobitto.core.expense.repository;

import com.probal.moddhobitto.core.expense.model.ExpenseSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseSubCategoryRepository extends JpaRepository<ExpenseSubCategory, Long> {
}
