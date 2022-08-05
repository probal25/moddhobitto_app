package com.probal.moddhobitto.core.userprofile.repository;

import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.userprofile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByUser(AppUser user);

    @Query(value = "select uec.name   `Category`\n" +
            "     , ec.name    `Main Category`\n" +
            "     , ubs.amount `Amount`\n" +
            "from user_balance_sheet ubs\n" +
            "         inner join user_expense_category uec on ubs.user_expense_category_id = uec.id\n" +
            "         inner join expense_category ec on uec.parent_expense_category_id = ec.id\n" +
            "         inner join app_user au on ubs.user_id = au.id\n" +
            "where ubs.user_id = ?1", nativeQuery = true )
    List<Object[]> getUserBalanceSheetByUserId(long userId);
}
