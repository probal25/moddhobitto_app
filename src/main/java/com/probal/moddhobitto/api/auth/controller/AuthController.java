package com.probal.moddhobitto.api.auth.controller;

import com.probal.moddhobitto.api.auth.dto.AppUserDto;
import com.probal.moddhobitto.core.response.ErrorResponse;
import com.probal.moddhobitto.core.response.JwtResponse;
import com.probal.moddhobitto.core.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(description = "User Signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody AppUserDto signUpRequest,
                                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorResponse.create(bindingResult, HttpStatus.BAD_REQUEST.value()));
        }

        if (authService.isUserExists(signUpRequest.getPhone())) {
            return ResponseEntity
                    .badRequest()
                    .body("Phone number is already taken!");
        }

        authService.createAppUser(signUpRequest);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    @Operation(description = "User login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AppUserDto loginRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(ErrorResponse.create(bindingResult, HttpStatus.BAD_REQUEST.value()));
        }
        JwtResponse response = authService.authenticateAppUser(loginRequest);
        return ResponseEntity.ok(response);
    }
}
