package com.probal.moddhobitto.api.auth.controller;

import com.probal.moddhobitto.api.auth.dto.AppUserDto;
import com.probal.moddhobitto.api.auth.response.ErrorResponse;
import com.probal.moddhobitto.api.auth.response.JwtResponse;
import com.probal.moddhobitto.api.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
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
