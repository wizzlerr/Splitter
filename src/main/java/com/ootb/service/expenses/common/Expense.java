package com.ootb.service.expenses.common;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by Adam on 2017-03-26.
 */
public abstract class Expense {
    public abstract Long getId();
    public abstract BigDecimal getExpense();
    public abstract Currency getCurrency();
}
