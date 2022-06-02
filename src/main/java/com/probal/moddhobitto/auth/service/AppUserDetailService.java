package com.probal.moddhobitto.auth.service;

import com.probal.moddhobitto.auth.entity.AppUser;
import com.probal.moddhobitto.auth.model.AppUserDetails;
import com.probal.moddhobitto.auth.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findAppUserByPhone(phone)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with phone number: " + phone));

        return AppUserDetails.build(appUser);
    }
}
