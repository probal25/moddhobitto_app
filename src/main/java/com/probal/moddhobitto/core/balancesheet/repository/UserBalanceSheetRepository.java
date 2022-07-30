package com.probal.moddhobitto.core.balancesheet.repository;

import com.probal.moddhobitto.core.balancesheet.model.UserBalanceSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceSheetRepository extends JpaRepository<UserBalanceSheet, Long> {
}
