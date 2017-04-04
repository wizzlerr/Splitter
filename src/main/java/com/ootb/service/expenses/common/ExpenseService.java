package com.ootb.service.expenses.common;

import com.ootb.service.expenses.personal.type.PersonalExpense;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Adam on 2017-03-26.
 */
public interface ExpenseService {
    void registerExpense(Expense expense);
    List<? extends Expense> getExpenses();
    Pair<?, ?> getExpensesPaged(int startIndex);
    void deleteExpense(Long id);
    PersonalExpense getExpense(Long id);
    void updateExpense(Expense expense);
    BigDecimal getTotalSum();
}
