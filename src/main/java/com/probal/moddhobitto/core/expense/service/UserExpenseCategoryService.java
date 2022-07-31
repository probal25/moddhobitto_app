package com.probal.moddhobitto.core.expense.service;

import com.probal.moddhobitto.api.expense.payload.UserExpenseCategoryPayload;
import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.balancesheet.service.UserBalanceSheetService;
import com.probal.moddhobitto.core.common.ActiveContextHolder;
import com.probal.moddhobitto.core.expense.model.ExpenseCategory;
import com.probal.moddhobitto.core.expense.model.UserExpenseCategory;
import com.probal.moddhobitto.core.expense.repository.UserExpenseCategoryRepository;
import com.probal.moddhobitto.core.userprofile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserExpenseCategoryService {

    private final ExpenseService expenseService;
    private final UserExpenseCategoryRepository userExpenseCategoryRepository;
    private final UserBalanceSheetService userBalanceSheetService;
    private final UserProfileService userProfileService;

    private final ActiveContextHolder activeContextHolder;

    @Transactional
    public void saveUserExpenseDetails(UserExpenseCategoryPayload userExpenseCategoryPayload, BindingResult bindingResult) {

        UserExpenseCategory userExpenseCategory = new UserExpenseCategory();

        userExpenseCategory.setName(userExpenseCategoryPayload.getName());
        userExpenseCategory.setDescription(userExpenseCategoryPayload.getDescription());
        ExpenseCategory expenseCategory = expenseService.getExpenseCategoryById(userExpenseCategoryPayload.getParentCategoryId());
        if (expenseCategory == null) {
            FieldError error = new FieldError("expenseSubCategoryPayload", "parentCategoryId", "Enter valid parent category id");
            bindingResult.addError(error);
            return;
        }
        userExpenseCategory.setParentExpenseCategory(expenseCategory);
        userExpenseCategory.setActive(true);
        userExpenseCategory.setType(userExpenseCategoryPayload.getType());
        userExpenseCategory.setUser(activeContextHolder.getLoggedInUser());

        userExpenseCategory = userExpenseCategoryRepository.save(userExpenseCategory);

        userBalanceSheetService.addDataToBalanceSheet(userExpenseCategory, userExpenseCategoryPayload.getAmount());

        userProfileService.addUserExpenseCategoryToUserProfile(userExpenseCategory);
    }

    public List<UserExpenseCategory> getUserExpenseCategories(AppUser user) {
        return userExpenseCategoryRepository.findAllByUser(user);
    }
}
