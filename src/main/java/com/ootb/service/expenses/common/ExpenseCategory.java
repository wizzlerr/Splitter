package com.ootb.service.expenses.common;

/**
 * Created by Adam on 2017-03-26.
 */
public enum ExpenseCategory {

    HOME("Dom"),
    CAR("Samochód"),
    OTHER("Inne");

    private final String displayName;

    ExpenseCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
