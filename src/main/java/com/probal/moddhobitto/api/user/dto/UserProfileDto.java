package com.probal.moddhobitto.api.user.dto;

import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.userprofile.model.UserProfile;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    public static UserProfileDto from(UserProfile userProfile) {
        return UserProfileDto.builder()
                .firstName(userProfile.getUser().getFirstName())
                .lastName(userProfile.getUser().getLastName())
                .phoneNumber(userProfile.getUser().getPhone())
                .build();
    }

}
