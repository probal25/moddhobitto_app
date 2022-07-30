package com.probal.moddhobitto.core.expense.repository;

import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.expense.model.UserExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExpenseCategoryRepository extends JpaRepository<UserExpenseCategory, Long> {

    List<UserExpenseCategory> findAllByUser(AppUser user);
}
