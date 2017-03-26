package com.ootb.web.expenses.personal;

import com.ootb.service.expenses.common.ExpenseCategory;
import com.ootb.web.expenses.personal.form.PersonalExpensesForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.stream.Collectors;

import static java.util.Currency.getAvailableCurrencies;

/**
 * Created by Adam on 2017-03-26.
 */
@Controller
@RequestMapping(value = "/auth")
public class PersonalExpensesController {

    @RequestMapping(value = "/my-expenses")
    public String personalExpenses() {

        return "expenses/personal/list";
    }

    @RequestMapping(value = "/my-expenses/new", method = RequestMethod.GET)
    public String newPersonalExpense(Model model) {
        model.addAttribute("personalExpensesForm", new PersonalExpensesForm());
        model.addAttribute("currencies", getAvailableCurrencies().stream()
                .sorted(Comparator.comparing(Currency::getCurrencyCode)).collect(Collectors.toList()));
        model.addAttribute("categories", Arrays.asList(ExpenseCategory.values()));
        return "expenses/personal/new";
    }

    @RequestMapping(value = "/my-expenses/new", method = RequestMethod.POST)
    public String createNewPersonalExpense() {

        return "expenses/personal/new";
    }
}
