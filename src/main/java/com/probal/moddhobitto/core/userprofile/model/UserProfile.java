package com.probal.moddhobitto.core.userprofile.model;

import com.probal.moddhobitto.core.auth.entity.AppUser;
import com.probal.moddhobitto.core.expense.model.UserExpenseCategory;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private AppUser user;

    @OneToMany
    private List<UserExpenseCategory> userExpenseCategories;
}
