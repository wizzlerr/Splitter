package com.ootb.web.friend;

import com.ootb.service.expenses.common.ExpensesService;
import com.ootb.service.infotainment.notification.NotificationService;
import com.ootb.service.profile.search.GroupAndProfileSearchService;
import com.ootb.service.profile.search.type.SearchOutcome;
import com.ootb.service.profile.user.ProfileService;
import com.ootb.service.profile.user.type.Profile;
import com.ootb.web.friend.form.FriendsListForm;
import com.ootb.web.technical.stereotype.AuthRequestMapping;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Adam on 2017-03-19.
 */
@Controller
@AuthRequestMapping
public class FriendListController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private GroupAndProfileSearchService groupAndProfileSearchService;

    @Autowired
    private ExpensesService expensesService;

    @RequestMapping(value = "/friends")
    public String friendList(Model model) {
        Pair<Integer, List<Profile>> pair = profileService.getProfilesPaged(0);
        setModel(model, pair);
        return "friends/list";
    }

    @RequestMapping(value = "/friends/page/{id}")
    public String friendsListPage(@PathVariable("id") int id, Model model) {
        Pair<Integer, List<Profile>> pair = profileService.getProfilesPaged((id*5) - 5);
        setModel(model, pair);
        return "friends/list";
    }

    private void setModel(Model model, Pair<Integer, List<Profile>> pair) {
        model.addAttribute("friends", pair.getValue());
        model.addAttribute("pages", pair.getKey());
        model.addAttribute("friendsForm", new FriendsListForm());
        model.addAttribute("youOwe", expensesService.getTotalYouOweDisplay());
        model.addAttribute("owedToYou", expensesService.getTotalOwedToYouDisplay());
    }

    @RequestMapping(value = "/friends/search", method = RequestMethod.POST)
    public String friendSearch(@ModelAttribute FriendsListForm friendsListForm, Model model) {
        if (friendsListForm.getSearchInput().isEmpty()) {
            notificationService.addWarningMessage("Brak sprecyzowanego zapytania");
            return "redirect:/auth/friends";
        }
        Pair<Integer, List<SearchOutcome>> pair = groupAndProfileSearchService.getNonFriendPaged(friendsListForm.getSearchInput(), 0);
        model.addAttribute("userList", pair.getValue());
        model.addAttribute("pages", pair.getKey());
        model.addAttribute("search", friendsListForm.getSearchInput());
        return "friends/list-found";
    }

    @RequestMapping(value = "/friends/search/{search}/{id}", method = RequestMethod.GET)
    public String friendSearch(@PathVariable("search") String search, @PathVariable("id") int id, Model model) {
        if (search.isEmpty()) {
            notificationService.addWarningMessage("Brak sprecyzowanego zapytania");
            return "redirect:/auth/friends";
        }
        Pair<Integer, List<SearchOutcome>> pair = groupAndProfileSearchService.getNonFriendPaged(search, (id*5) - 5);
        model.addAttribute("userList", pair.getValue());
        model.addAttribute("pages", pair.getKey());
        model.addAttribute("search", search);
        return "friends/list-found";
    }
}