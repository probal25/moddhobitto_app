package com.probal.moddhobitto.core.userprofile.repository;

import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.userprofile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByUser(AppUser user);
}
