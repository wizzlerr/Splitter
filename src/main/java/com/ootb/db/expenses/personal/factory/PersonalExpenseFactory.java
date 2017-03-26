package com.ootb.db.expenses.personal.factory;

import com.beust.jcommander.internal.Lists;
import com.ootb.db.expenses.personal.type.PersonalExpense;
import com.ootb.db.user.type.User;
import com.ootb.service.expenses.common.ExpenseCategory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

import static com.ootb.service.expenses.personal.type.PersonalExpense.PersonalExpenseBuilder.aPersonalExpense;

/**
 * Created by Adam on 2017-03-26.
 */
@Component
public class PersonalExpenseFactory {

    public PersonalExpense getPersonalExpense(com.ootb.service.expenses.personal.type.PersonalExpense personalExpense, User user) {
        PersonalExpense personalExpense1 = new PersonalExpense();
        personalExpense1.setCategory(personalExpense.getCategory().name());
        personalExpense1.setCurrency(personalExpense.getCurrency());
        personalExpense1.setDate(personalExpense.getDate().toString());
        personalExpense1.setDescription(personalExpense.getDescription());
        personalExpense1.setExpense(personalExpense.getExpense());
        personalExpense1.setName(personalExpense.getName());
        personalExpense1.setUser(user);

        return personalExpense1;
    }

    public List<com.ootb.service.expenses.personal.type.PersonalExpense> getPersonalExpense(List<PersonalExpense> personalExpenses) {
        List<com.ootb.service.expenses.personal.type.PersonalExpense> expenses = Lists.newArrayList();

        personalExpenses.forEach(personalExpense -> expenses.add(aPersonalExpense().withCategory(ExpenseCategory.valueOf(personalExpense.getCategory())).withCurrency(personalExpense.getCurrency())
            .withDate(LocalDate.parse(personalExpense.getDate())).withDescription(personalExpense.getDescription()).withExpense(personalExpense.getExpense())
            .withName(personalExpense.getName()).withId(personalExpense.getId()).build()));

        return expenses;
    }

    public com.ootb.service.expenses.personal.type.PersonalExpense getPersonalExpense(PersonalExpense personalExpense) {
        return aPersonalExpense().withCategory(ExpenseCategory.valueOf(personalExpense.getCategory())).withCurrency(personalExpense.getCurrency())
                .withDate(LocalDate.parse(personalExpense.getDate())).withDescription(personalExpense.getDescription()).withExpense(personalExpense.getExpense())
                .withName(personalExpense.getName()).withId(personalExpense.getId()).build();
    }
}
