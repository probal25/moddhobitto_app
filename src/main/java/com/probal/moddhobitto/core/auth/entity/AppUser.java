package com.probal.moddhobitto.core.auth.entity;


import com.probal.moddhobitto.core.auth.model.enums.AppUserRole;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String phone;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    @ElementCollection
    private Set<AppUserRole> roles;

    private boolean active;

    private Date createdAt;

    private Date updatedAt;

    private String customField1;

    private String customField2;

    private String customField3;
}
