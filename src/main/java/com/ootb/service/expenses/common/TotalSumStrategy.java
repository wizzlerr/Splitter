package com.ootb.service.expenses.common;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Adam on 2017-03-30.
 */
public interface TotalSumStrategy {

    BigDecimal getTotalSum(List<?> expenses);

    String getTotalSumDisplay(BigDecimal expense);

    String getTotalSumDisplay(List<?> expenses);
}
