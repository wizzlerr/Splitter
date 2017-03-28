package com.ootb.web.expenses.personal;

import com.ootb.service.currency.CurrencyService;
import com.ootb.service.expenses.common.ExpenseCategory;
import com.ootb.service.expenses.personal.PersonalExpenseService;
import com.ootb.service.expenses.personal.type.PersonalExpense;
import com.ootb.web.expenses.personal.form.PersonalExpensesForm;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

import static com.ootb.service.expenses.personal.type.PersonalExpense.PersonalExpenseBuilder.aPersonalExpense;
import static java.util.Currency.getAvailableCurrencies;

/**
 * Created by Adam on 2017-03-26.
 */
@Controller
@RequestMapping(value = "/auth")
public class PersonalExpensesController {

    @Autowired
    private PersonalExpenseService personalExpenseService;

    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/my-expenses")
    public String personalExpenses(Model model) {
        Pair<Integer, List<PersonalExpense>> pair = personalExpenseService.getExpensesPaged(0);
        model.addAttribute("expenses", pair.getValue());
        model.addAttribute("pages", pair.getKey());
        return "expenses/personal/list";
    }

    @RequestMapping(value = "/my-expenses/page/{id}")
    public String personalExpensesPage(@PathVariable("id") int id, Model model) {
        Pair<Integer, List<PersonalExpense>> pair = personalExpenseService.getExpensesPaged((id*5) - 5);
        model.addAttribute("expenses", pair.getValue());
        model.addAttribute("pages", pair.getKey());
        return "expenses/personal/list";
    }

    @RequestMapping(value = "/my-expenses/new", method = RequestMethod.GET)
    public String newPersonalExpense(Model model) {
        model.addAttribute("personalExpensesForm", new PersonalExpensesForm());
        model.addAttribute("currencies", currencyService.getSortedCurrencies());
        model.addAttribute("categories", Arrays.asList(ExpenseCategory.values()));
        return "expenses/personal/new";
    }

    @RequestMapping(value = "/my-expenses/new", method = RequestMethod.POST)
    public String createNewPersonalExpense(@ModelAttribute @Valid PersonalExpensesForm personalExpensesForm) {
        personalExpenseService.registerExpense(aPersonalExpense().withExpense(personalExpensesForm.getExpense()).withCategory(personalExpensesForm.getCategory()).withCurrency(personalExpensesForm.getCurrency()).withDate(LocalDate.parse(personalExpensesForm.getDate())).withDescription(personalExpensesForm.getDescription()).withName(personalExpensesForm.getName()).build());
        return "redirect:/auth/my-expenses";
    }

    @RequestMapping(value = "/my-expenses/{id}/delete")
    public String deleteExpense(@PathVariable("id") Long id) {
        personalExpenseService.deleteExpense(id);
        return "redirect:/auth/my-expenses";
    }

    @RequestMapping(value = "/my-expenses/{id}/edit", method = RequestMethod.GET)
    public String editExpense(@PathVariable("id") Long id, Model model) {
        model.addAttribute("personalExpensesForm", new PersonalExpensesForm(personalExpenseService.getExpense(id)));
        model.addAttribute("currencies", getAvailableCurrencies().stream()
                .sorted(Comparator.comparing(Currency::getCurrencyCode)).collect(Collectors.toList()));
        model.addAttribute("categories", Arrays.asList(ExpenseCategory.values()));
        model.addAttribute("id", id);
        return "expenses/personal/edit";
    }

    @RequestMapping(value = "/my-expenses/{id}/edit", method = RequestMethod.POST)
    public String editGivenExpense(@PathVariable("id") Long id, @ModelAttribute @Valid PersonalExpensesForm personalExpensesForm) {
        personalExpenseService.updateExpense(aPersonalExpense().withExpense(personalExpensesForm.getExpense()).withCategory(personalExpensesForm.getCategory()).withCurrency(personalExpensesForm.getCurrency()).withDate(LocalDate.parse(personalExpensesForm.getDate())).withDescription(personalExpensesForm.getDescription()).withName(personalExpensesForm.getName()).withId(id).build());
        return "redirect:/auth/my-expenses";
    }
}
