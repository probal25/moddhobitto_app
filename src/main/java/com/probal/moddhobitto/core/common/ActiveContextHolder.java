package com.probal.moddhobitto.core.common;

import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.auth.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActiveContextHolder {

    public final AppUserRepository appUserRepository;

    public AppUser getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        return appUserRepository.findAppUserByPhone(username).orElse(null);
    }
}
