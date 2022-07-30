package com.probal.moddhobitto.core.expense.enums;

public enum Type {
    INCOME("income"),
    EXPENSE("expense");

    private String value;

    private Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
