package com.ootb.service.expenses.friend;

import com.ootb.service.expenses.common.Expense;
import com.ootb.service.expenses.common.ExpenseService;
import com.ootb.service.expenses.personal.type.PersonalExpense;
import javafx.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Adam on 2017-04-02.
 */
@Service
public class FriendExpenseService implements ExpenseService{


    @Override
    public void registerExpense(Expense expense) {

    }

    @Override
    public List<? extends Expense> getExpenses() {
        return null;
    }

    @Override
    public Pair<?, ?> getExpensesPaged(int startIndex) {
        return null;
    }

    @Override
    public void deleteExpense(Long id) {

    }

    @Override
    public PersonalExpense getExpense(Long id) {
        return null;
    }

    @Override
    public void updateExpense(Expense expense) {

    }

    @Override
    public BigDecimal getTotalSum() {
        return null;
    }
}
