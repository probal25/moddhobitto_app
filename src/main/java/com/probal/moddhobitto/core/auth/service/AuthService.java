package com.probal.moddhobitto.core.auth.service;

import com.probal.moddhobitto.api.auth.dto.AppUserDto;
import com.probal.moddhobitto.core.response.JwtResponse;
import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.auth.model.AppUserDetails;
import com.probal.moddhobitto.core.auth.model.enums.AppUserRole;
import com.probal.moddhobitto.core.auth.repository.AppUserRepository;
import com.probal.moddhobitto.core.auth.util.JwtUtils;
import com.probal.moddhobitto.core.userprofile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder encoder;

    private final AppUserRepository appUserRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final UserProfileService userProfileService;

    public boolean isUserExists(String phone) {
        AppUser appUser = appUserRepository.findAppUserByPhone(phone).orElse(null);
        return appUser != null;
    }

    @Transactional
    public void createAppUser(AppUserDto appUserDto) {
        AppUser appUser = new AppUser();
        appUser.setPhone(appUserDto.getPhone());
        appUser.setPassword(encoder.encode(appUserDto.getPassword()));
        appUser.setRoles(Collections.singleton(AppUserRole.APP_ROLE));
        appUser.setCreatedAt(new Date());
        appUser.setActive(true);
        AppUser user = appUserRepository.save(appUser);
        userProfileService.createUserProfileForAppUser(user);
    }

    public JwtResponse authenticateAppUser(AppUserDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getPhone(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
    }

}
