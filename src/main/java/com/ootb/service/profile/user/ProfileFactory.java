package com.ootb.service.profile.user;

import com.ootb.db.user.type.User;
import com.ootb.service.friends.type.Friend;
import com.ootb.service.profile.user.type.Profile;
import com.ootb.web.expenses.divided.DividedExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.ootb.service.profile.user.type.Profile.ProfileBuilder.aProfile;

/**
 * Created by Adam on 2017-04-04.
 */
@Component
public class ProfileFactory {

    @Autowired
    private DividedExpenseService dividedExpenseService;

    public Profile getProfile(User user, BigDecimal youOwe, BigDecimal owedToYou, BigDecimal personalExpenses) {
        return aProfile().withNick(user.getUserName()).withOwnedToYou(owedToYou)
                .withYouOwe(youOwe).withPersonalExpense(personalExpenses)
                .withBalance(owedToYou.subtract(youOwe))
                .withIsFriend(false).withIsYou(true).build();
    }

    public Profile getProfile(User user, BigDecimal youOwe, BigDecimal owedToYou, boolean isFriend) {
        return aProfile().withNick(user.getUserName()).withOwnedToYou(owedToYou)
                .withYouOwe(youOwe).withBalance(owedToYou.subtract(youOwe))
                .withIsFriend(isFriend).withIsYou(false).build();
    }

    public List<Profile> getProfile(List<Friend> friends, User loggedUser) {
        List<Profile> profiles = new ArrayList<>();

        for(Friend friend : friends) {
            profiles.add(getProfile(friend));
        }
        return profiles;
    }

    private Profile getProfile(Friend friend) {
        return aProfile().withNick(friend.getNick()).withBalance(dividedExpenseService.getBalance(friend)).build();
    }
}
