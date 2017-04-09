package com.ootb.db.friends.factory;

import com.ootb.db.user.type.User;
import com.ootb.service.friends.type.Friend;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.ootb.service.friends.type.Friend.FriendBuilder.aFriend;

/**
 * Created by Adam on 2017-04-02.
 */
@Component
public class FriendsFactory {

    public List<Friend> getFriends(List<com.ootb.db.friends.type.Friend> objects, User user) {
        List<Friend> friends = new ArrayList<>();

        objects.forEach(friend -> friends.add(getFriend(friend, user)));

        return friends;
    }

    public Friend getFriend(com.ootb.db.friends.type.Friend friend, User user) {
        if(user.getUserName().equals(friend.getFirstUser().getUserName())) {
            return aFriend().
                    withId(Long.valueOf(friend.getSecondUser().getId())).
                    withNick(friend.getSecondUser().getUserName()).build();
        }

        return aFriend().
                withId(Long.valueOf(friend.getFirstUser().getId())).
                withNick(friend.getFirstUser().getUserName()).build();
    }

    public Friend getFriend(User user) {
        return aFriend().
                withId(Long.valueOf(user.getId())).
                withNick(user.getUserName()).build();
    }

    public List<Friend> getFriends(List<com.ootb.db.friends.type.Friend> objects) {
        List<Friend> friends = new ArrayList<>();

        objects.forEach(friend -> friends.add(getFriend(friend)));

        return friends;
    }

    public Friend getFriend(com.ootb.db.friends.type.Friend friend) {
        return aFriend().
                withId(Long.valueOf(friend.getSecondUser().getId())).
                withNick(friend.getSecondUser().getUserName()).build();
    }
}
