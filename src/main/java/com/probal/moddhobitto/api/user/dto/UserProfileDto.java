package com.probal.moddhobitto.api.user.dto;

import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.expense.model.UserExpenseCategory;
import com.probal.moddhobitto.core.userprofile.model.UserProfile;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserProfileDto {

    private String phoneNumber;

    private List<UserExpenseCategoryDto> userExpenseCategoryDtos;

    private List<UserBalanceSheetDto> userBalanceSheetDtos;



    public static UserProfileDto from(UserProfile userProfile,
                                      List<UserBalanceSheetDto> userBalanceSheetDtos,
                                      List<UserExpenseCategory> expenseCategories) {
        return UserProfileDto.builder()
                .phoneNumber(userProfile.getUser().getPhone())
                .userBalanceSheetDtos(userBalanceSheetDtos)
                .userExpenseCategoryDtos(expenseCategories.stream()
                        .map(UserExpenseCategoryDto::from).collect(Collectors.toList()))
                .build();
    }

}
