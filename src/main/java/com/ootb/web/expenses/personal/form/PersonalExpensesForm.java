package com.ootb.web.expenses.personal.form;

import com.ootb.service.expenses.common.ExpenseCategory;
import com.ootb.service.expenses.personal.type.PersonalExpense;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by Adam on 2017-03-26.
 */
public class PersonalExpensesForm {

    @NotNull
    private String name;

    private String description;

    @NotNull
    private BigDecimal expense;

    @NotNull
    private Currency currency;

    @NotNull
    private ExpenseCategory category;

    @NotNull
    private String date;

    public PersonalExpensesForm() {
    }

    public PersonalExpensesForm(PersonalExpense expense) {
        this.name = expense.getName();
        this.description = expense.getDescription();
        this.expense = expense.getExpense();
        this.currency = expense.getCurrency();
        this.category = expense.getCategory();
        this.date = expense.getDate().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
