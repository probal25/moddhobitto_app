package com.probal.moddhobitto.core.expense.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table(name = "expense_sub_category")
public class ExpenseSubCategory {

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

    private String customField1;

    private String customField2;

    private String customField3;
}
