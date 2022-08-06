package com.probal.moddhobitto.api.user.controller;


import com.probal.moddhobitto.api.user.dto.UserBalanceSheetDto;
import com.probal.moddhobitto.api.user.dto.UserProfileDto;
import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.common.ActiveContextHolder;
import com.probal.moddhobitto.core.expense.model.UserExpenseCategory;
import com.probal.moddhobitto.core.expense.service.UserExpenseCategoryService;
import com.probal.moddhobitto.core.userprofile.model.UserProfile;
import com.probal.moddhobitto.core.userprofile.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserProfileController {


    private final ActiveContextHolder activeContextHolder;

    private final UserProfileService profileService;

    private final UserExpenseCategoryService userExpenseCategoryService;

    @GetMapping("/user_profile")
    @Operation(description = "Read Logged In User Profile")
    public ResponseEntity<?> getUserProfile() {

        AppUser loggedInUser = activeContextHolder.getLoggedInUser();
        if (loggedInUser == null) {
            return ResponseEntity
                    .badRequest()
                    .body("No logged in user found");
        }

        UserProfile userProfile = profileService
                .getUserProfileByAppUser(loggedInUser);
        List<UserBalanceSheetDto> userBalanceSheet = profileService
                .getUserBalanceSheet(loggedInUser);
        List<UserExpenseCategory> userExpenseCategories = userExpenseCategoryService
                .getUserExpenseCategories(loggedInUser);

        UserProfileDto userProfileDto = UserProfileDto
                .from(userProfile, userBalanceSheet,userExpenseCategories);

        return ResponseEntity.ok(userProfileDto);
    }

    @GetMapping("/user_balance_sheet")
    @Operation(description = "Get user Balance Sheet")
    public ResponseEntity<?> getUserBalanceSheet() {
        AppUser loggedInUser = activeContextHolder.getLoggedInUser();

        return ResponseEntity.ok(profileService.getUserBalanceSheet(loggedInUser));

    }

}
