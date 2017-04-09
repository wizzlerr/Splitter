package com.ootb.service.profile.user;

import com.ootb.db.user.type.User;
import com.ootb.service.profile.user.type.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.ootb.service.profile.user.type.Profile.ProfileBuilder.aProfile;

/**
 * Created by Adam on 2017-04-04.
 */
@Component
public class ProfileFactory {

    public Profile getProfile(User user, BigDecimal youOwe, BigDecimal owedToYou, BigDecimal personalExpenses) {
        return aProfile().withNick(user.getUserName()).withOwnedToYou(owedToYou)
                .withYouOwe(youOwe).withPersonalExpense(personalExpenses)
                .withBalance(owedToYou.subtract(youOwe))
                .withIsFriend(false).withIsYou(true).build();
    }
}
