package com.probal.moddhobitto.core.auth.model.enums;

public enum AppUserRole {

    ADMIN_ROLE("Admin user"),
    APP_ROLE("App user");

    public final String label;

    private AppUserRole(String label) {
        this.label = label;
    }

    public String getLabel(AppUserRole role) {
        return role.label;
    }
}
