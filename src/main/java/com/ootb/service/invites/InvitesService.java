package com.ootb.service.invites;

import com.ootb.db.event.type.Event;
import com.ootb.db.friends.type.Friend;
import com.ootb.db.user.type.User;
import com.ootb.db.util.Page;
import com.ootb.service.event.EventService;
import com.ootb.service.friends.FriendsService;
import com.ootb.service.invites.factory.InviteFactory;
import com.ootb.service.invites.type.Invite;
import com.ootb.service.user.UserService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Adam on 2017-04-17.
 */
@Service
public class InvitesService {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private InviteFactory inviteFactory;

    public List<Invite> getInvites() {
        User loggedUSe4r = userService.getLoggedUser();
        List<Event> events = eventService.getInviteEventsByUser(loggedUSe4r);
        List<Friend> friends = friendsService.getPendingFriends();
        List<?> groups = null;
        return inviteFactory.getInvites(events, friends, groups);
    }

    public Pair<Integer, List<Invite>> getFriendsInvitesPaged(int startIndex) {
        List<Friend> friends = friendsService.getPendingFriends();
        List<Invite> invites = inviteFactory.getInvites(friends);
        Page page = new Page(invites, startIndex);
        return new Pair<Integer, List<Invite>>(page.getTotalPages(), (List<Invite>)page.getObjects());
    }
}
