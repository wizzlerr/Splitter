package com.ootb.service.expenses.common;

import com.ootb.service.expenses.friend.FriendExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Adam on 2017-04-04.
 */
@Service
public class ExpensesService {

    @Autowired
    private FriendExpenseService friendExpenseService;

    public BigDecimal getTotalOwedToYou() {
        BigDecimal sum = BigDecimal.ZERO;

        return sum;
    }

    public BigDecimal getTotalYouOwe() {
        BigDecimal sum = BigDecimal.ZERO;

        return sum;
    }

    public String getTotalOwedToYouDisplay() {
        return "Należne Ci " + getTotalOwedToYou().toString() + " PLN";
    }

    public String getTotalYouOweDisplay() {
        return "Należysz " + getTotalYouOwe().toString() + " PLN";
    }

    public BigDecimal getTotalYouOwe(String nick) {
        BigDecimal sum = BigDecimal.ZERO;

        return sum;
    }

    public BigDecimal getTotalOwedToYou(String nick) {
        BigDecimal sum = BigDecimal.ZERO;

        return sum;
    }
}
