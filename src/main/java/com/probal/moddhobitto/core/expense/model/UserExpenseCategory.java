package com.probal.moddhobitto.core.expense.model;

import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.expense.enums.Type;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "user_expense_category")
public class UserExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    @ManyToOne
    private ExpenseCategory parentExpenseCategory;

    private boolean active;

    private Date createdAt;

    private Date updatedAt;

    @ManyToOne
    private AppUser user;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    private String customField3;
}
