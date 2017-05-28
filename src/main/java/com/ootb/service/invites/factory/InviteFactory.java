package com.ootb.service.invites.factory;

import com.ootb.db.event.type.Event;
import com.ootb.db.friends.type.Friend;
import com.ootb.service.invites.type.Invite;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2017-04-17.
 */
@Component
public class InviteFactory {
    public List<Invite> getInvites(List<Event> events, List<Friend> friends, List<?> groups) {
        return null;
    }

    public List<Invite> getInvites(List<Friend> friends) {
        List<Invite> invites = new ArrayList<>();

        for(Friend friend:friends) {
            invites.add(getInvite(friend));
        }

        return invites;
    }

    private Invite getInvite(Friend friend) {
        Invite invite = new Invite();
        invite.setInviteDefinition("Nowe zaproszenie od " + friend.getFirstUser().getUserName());
        invite.setInviteAction("/auth/friend/confirm/" + friend.getId());
        return invite;
    }
}
