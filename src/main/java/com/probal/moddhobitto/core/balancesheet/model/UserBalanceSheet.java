package com.probal.moddhobitto.core.balancesheet.model;

import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.expense.model.UserExpenseCategory;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "user_balance_sheet")
public class UserBalanceSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal amount;

    private Date createdAt;

    @ManyToOne
    private UserExpenseCategory userExpenseCategory;

    @ManyToOne
    private AppUser user;

}
