package com.ootb.db.expenses.personal.dao;

import com.ootb.db.user.type.User;
import com.ootb.db.util.AbstractFilter;

import java.util.Currency;
import java.util.Date;

/**
 * Created by Adam on 2017-03-26.
 */
public class PersonalExpenseFilter extends AbstractFilter {

    private Long id;

    private User user;

    private String name;

    private Currency currency;

    private String category;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public static final class PersonalExpenseFilterBuilder {
        private Long id;
        private User user;
        private String name;
        private Currency currency;
        private String category;
        private Date date;

        private PersonalExpenseFilterBuilder() {
        }

        public static PersonalExpenseFilterBuilder aPersonalExpenseFilter() {
            return new PersonalExpenseFilterBuilder();
        }

        public PersonalExpenseFilterBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PersonalExpenseFilterBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public PersonalExpenseFilterBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonalExpenseFilterBuilder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public PersonalExpenseFilterBuilder withCategory(String category) {
            this.category = category;
            return this;
        }

        public PersonalExpenseFilterBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public PersonalExpenseFilter build() {
            PersonalExpenseFilter personalExpenseFilter = new PersonalExpenseFilter();
            personalExpenseFilter.setId(id);
            personalExpenseFilter.setUser(user);
            personalExpenseFilter.setName(name);
            personalExpenseFilter.setCurrency(currency);
            personalExpenseFilter.setCategory(category);
            personalExpenseFilter.setDate(date);
            return personalExpenseFilter;
        }
    }
}
