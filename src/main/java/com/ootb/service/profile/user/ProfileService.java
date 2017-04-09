package com.ootb.service.profile.user;

import com.ootb.db.user.type.User;
import com.ootb.db.util.Page;
import com.ootb.service.expenses.common.ExpensesService;
import com.ootb.service.expenses.personal.PersonalExpenseService;
import com.ootb.service.friends.FriendsService;
import com.ootb.service.friends.type.Friend;
import com.ootb.service.profile.user.type.Profile;
import com.ootb.service.user.UserService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Adam on 2017-04-04.
 */
@Service
public class ProfileService {

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private ProfileFactory profileFactory;

    @Autowired
    private ExpensesService expensesService;

    @Autowired
    private UserService userService;

    @Autowired
    private PersonalExpenseService personalExpenseService;

    public Pair<Integer, List<Profile>> getProfilesPaged(int startIndex) {
        List<Friend> friends = friendsService.getFriends();
        Page page = new Page(friends, startIndex);
        return new Pair<>(page.getTotalPages(), getProfiles((List<Friend>) page.getObjects()));
    }

    public List<Profile> getProfiles(List<Friend> friends) {
        return null;
    }

    public Profile getProfile(int userId) {
        return null;
    }

    public Profile getProfile(String nick) {
        return null;
    }

    public Profile getProfile() {
        User user = userService.getLoggedUser();
        BigDecimal youOwe = expensesService.getTotalYouOwe();
        BigDecimal owedToYou = expensesService.getTotalOwedToYou();
        BigDecimal personalExpenses = personalExpenseService.getTotalSum();
        return profileFactory.getProfile(user, youOwe, owedToYou, personalExpenses);
    }
}
