package com.ootb.web.expenses.divided;

import com.ootb.service.friends.type.Friend;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Adam on 2017-05-28.
 */
@Service
public class DividedExpenseService {

    public BigDecimal getBalance(Friend friend) {
        return BigDecimal.ZERO;
    }
}
