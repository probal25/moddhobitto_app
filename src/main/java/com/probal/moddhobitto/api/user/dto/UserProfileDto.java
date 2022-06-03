package com.probal.moddhobitto.api.user.dto;

import com.probal.moddhobitto.core.auth.entity.AppUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    public static UserProfileDto from(AppUser appUser) {
        return UserProfileDto.builder()
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .phoneNumber(appUser.getPhone())
                .build();
    }

}
