package com.ootb.web.invites;

import com.ootb.service.invites.InvitesService;
import com.ootb.service.invites.type.Invite;
import com.ootb.web.technical.stereotype.AuthRequestMapping;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Adam on 2017-04-17.
 */
@Controller
@AuthRequestMapping
public class InvitesController {

    @Autowired
    private InvitesService invitesService;

    @RequestMapping(value = "/invites")
    public String invites(Model model) {
        Pair<Integer, List<Invite>> pair = invitesService.getFriendsInvitesPaged(0);
        setModel(model, pair);
        return "invites/list";
    }

    private void setModel(Model model, Pair<Integer, List<Invite>> pair) {
        model.addAttribute("invites", pair.getValue());
        model.addAttribute("pages", pair.getKey());
    }

    @RequestMapping(value = "/invites/page/{id}")
    public String personalExpensesPage(@PathVariable("id") int id, Model model) {
        Pair<Integer, List<Invite>> pair = invitesService.getFriendsInvitesPaged((id*5) - 5);
        setModel(model, pair);
        return "invites/list";
    }

}
