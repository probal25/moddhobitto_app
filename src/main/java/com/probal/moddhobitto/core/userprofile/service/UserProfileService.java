package com.probal.moddhobitto.core.userprofile.service;


import com.probal.moddhobitto.api.user.dto.UserBalanceSheetDto;
import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.expense.model.UserExpenseCategory;
import com.probal.moddhobitto.core.userprofile.model.UserProfile;
import com.probal.moddhobitto.core.userprofile.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;


    @Transactional
    public void createUserProfileForAppUser(AppUser user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);

        userProfileRepository.save(userProfile);
    }

    public UserProfile getUserProfileByAppUser(AppUser user) {
        return userProfileRepository.findByUser(user);
    }

    @Transactional
    public void addUserExpenseCategoryToUserProfile(UserExpenseCategory userExpenseCategory) {

        UserProfile userProfile = getUserProfileByAppUser(userExpenseCategory.getUser());

        List<UserExpenseCategory> userExpenseCategories = userProfile.getUserExpenseCategories();
        userExpenseCategories.add(userExpenseCategory);

        userProfileRepository.save(userProfile);

    }
    public List<UserBalanceSheetDto> getUserBalanceSheet(AppUser loggedInUser) {
        List<Object[]> userBalanceSheets = userProfileRepository.getUserBalanceSheetByUserId(loggedInUser.getId());
        List<UserBalanceSheetDto> userBalanceSheetDtos = new ArrayList<>();
        for (Object[] o : userBalanceSheets) {
            UserBalanceSheetDto userBalanceSheetDto = UserBalanceSheetDto.builder()
                    .amount((BigDecimal) o[2])
                    .categoryName((String) o[0])
                    .parentCategoryName((String) o[1])
                    .build();
            userBalanceSheetDtos.add(userBalanceSheetDto);
        }

        return userBalanceSheetDtos;
    }
}
