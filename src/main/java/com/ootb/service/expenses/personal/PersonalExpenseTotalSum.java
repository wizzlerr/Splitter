package com.ootb.service.expenses.personal;

import com.ootb.service.currency.CurrencyService;
import com.ootb.service.expenses.common.Expense;
import com.ootb.service.expenses.common.TotalSumStrategy;
import com.ootb.service.expenses.personal.type.PersonalExpense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by Adam on 2017-03-30.
 */
@Component
public class PersonalExpenseTotalSum implements TotalSumStrategy {

    @Autowired
    private CurrencyService currencyService;

    @Override
    public BigDecimal getTotalSum(List<?> expenses) {
        List<PersonalExpense> personalExpenses = (List<PersonalExpense>) expenses;

        BigDecimal sum = BigDecimal.ZERO;

        for(Expense expense: personalExpenses) {
            sum = sum.add(currencyService.getConvertedCurrencyToPLN(expense.getExpense(), expense.getCurrency()));
        }

        return sum;
    }

    @Override
    public String getTotalSumDisplay(BigDecimal expense) {
        return "Łączna suma: " + expense.setScale(2, RoundingMode.HALF_EVEN).toString() + " PLN";
    }

    @Override
    public String getTotalSumDisplay(List<?> expenses) {
        return getTotalSumDisplay(getTotalSum(expenses));
    }
}
