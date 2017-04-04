package com.ootb.service.expenses.personal.type;

import com.ootb.service.expenses.common.Expense;
import com.ootb.service.expenses.common.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

/**
 * Created by Adam on 2017-03-26.
 */
public class PersonalExpense extends Expense {

    private Long id;

    private String name;

    private String description;

    private BigDecimal expense;

    private Currency currency;

    private ExpenseCategory category;

    private LocalDate date;

    public PersonalExpense() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public static final class PersonalExpenseBuilder {
        private Long id;
        private String name;
        private String description;
        private BigDecimal expense;
        private Currency currency;
        private ExpenseCategory category;
        private LocalDate date;

        private PersonalExpenseBuilder() {
        }

        public static PersonalExpenseBuilder aPersonalExpense() {
            return new PersonalExpenseBuilder();
        }

        public PersonalExpenseBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PersonalExpenseBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonalExpenseBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public PersonalExpenseBuilder withExpense(BigDecimal expense) {
            this.expense = expense;
            return this;
        }

        public PersonalExpenseBuilder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public PersonalExpenseBuilder withCategory(ExpenseCategory category) {
            this.category = category;
            return this;
        }

        public PersonalExpenseBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public PersonalExpense build() {
            PersonalExpense personalExpense = new PersonalExpense();
            personalExpense.setId(id);
            personalExpense.setName(name);
            personalExpense.setDescription(description);
            personalExpense.setExpense(expense);
            personalExpense.setCurrency(currency);
            personalExpense.setCategory(category);
            personalExpense.setDate(date);
            return personalExpense;
        }
    }
}
