package com.ootb.service.expenses.common;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by Adam on 2017-03-26.
 */
public interface Expense {
    BigDecimal getExpense();
    Currency getCurrency();
}
