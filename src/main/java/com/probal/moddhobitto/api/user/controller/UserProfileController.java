package com.probal.moddhobitto.api.user.controller;


import com.probal.moddhobitto.api.user.dto.UserProfileDto;
import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.common.ActiveContextHolder;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserProfileController {


    private final ActiveContextHolder activeContextHolder;

    @GetMapping("/user_profile")
    @Operation(description = "Read Logged In User Profile")
    public ResponseEntity<?> getUserProfile() {

        AppUser loggedInUser = activeContextHolder.getLoggedInUserName();
        if (loggedInUser == null) {
            return ResponseEntity
                    .badRequest()
                    .body("No logged in user found");
        }

        UserProfileDto userProfileDto = UserProfileDto.from(loggedInUser);

        return ResponseEntity.ok(userProfileDto);
    }

}
