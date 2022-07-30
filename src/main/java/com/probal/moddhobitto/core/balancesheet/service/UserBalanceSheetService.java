package com.probal.moddhobitto.core.balancesheet.service;

import com.probal.moddhobitto.core.balancesheet.model.UserBalanceSheet;
import com.probal.moddhobitto.core.balancesheet.repository.UserBalanceSheetRepository;
import com.probal.moddhobitto.core.expense.model.UserExpenseCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserBalanceSheetService {

    private final UserBalanceSheetRepository repository;


    @Transactional
    public void addDataToBalanceSheet(UserExpenseCategory userExpenseCategory, BigDecimal amount) {

        UserBalanceSheet userBalanceSheet = new UserBalanceSheet();

        userBalanceSheet.setAmount(amount);
        userBalanceSheet.setUser(userExpenseCategory.getUser());
        userBalanceSheet.setUserExpenseCategory(userExpenseCategory);
        userBalanceSheet.setCreatedAt(new Date());

        repository.save(userBalanceSheet);

    }
}
