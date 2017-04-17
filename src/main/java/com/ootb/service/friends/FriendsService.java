package com.ootb.service.friends;

import com.ootb.db.friends.dao.FriendsDao;
import com.ootb.db.friends.factory.FriendsFactory;
import com.ootb.db.user.type.User;
import com.ootb.db.util.Page;
import com.ootb.service.event.EventService;
import com.ootb.service.friends.type.Friend;
import com.ootb.service.infotainment.notification.NotificationService;
import com.ootb.service.user.UserService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ootb.db.friends.type.Friend.FriendBuilder.aFriend;

/**
 * Created by Adam on 2017-04-02.
 */
@Service
public class FriendsService {

    @Autowired
    private FriendsDao friendsDao;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendsFactory friendsFactory;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EventService eventService;

    public Pair<Integer, List<Friend>> getFriendsPaged(int startIndex) {
        Page page = friendsDao.findByUserPaged(userService.getLoggedUser(), startIndex);
        return new Pair<>(page.getTotalPages(), friendsFactory.getFriends((List<com.ootb.db.friends.type.Friend>) page.getObjects()));
    }

    public List<Friend> getFriends() {
        User loggedUser = userService.getLoggedUser();
        return friendsFactory.getFriends(friendsDao.findByUser(loggedUser), loggedUser);
    }

    public List<Friend> getNonFriends() {
        List<User> allUsers = userService.getEnabledUsers();
        List<Friend> friends = getFriends();
        List<Friend> result = new ArrayList<>();

        for(User user : allUsers) {
            Friend friend = friendsFactory.getFriend(user);
            if(!friends.contains(friend)) {
                result.add(friend);
            }
        }
        return result;
    }

    public boolean isFriend(String nick) {
        List<Friend> friends = getFriends();

        for(Friend friend : friends) {
            if(friend.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void addFriend(String name) {
        User loggedUser = userService.getLoggedUser();
        User targetUser = userService.getUser(name);

        friendsDao.saveFriendship(aFriend().withFirstUser(loggedUser).withSecondUser(targetUser).build());
        eventService.addFriendInvitationEvent(targetUser);
        notificationService.addInfoMessage("Wysłano zaproszenie do znajomych!");
    }

    public void removeFriend(String name) {
        if(friendsDao.remove(userService.getLoggedUser(), userService.getUser(name))) {
            notificationService.addInfoMessage("Usunięto znajomego");
        }
    }
}
