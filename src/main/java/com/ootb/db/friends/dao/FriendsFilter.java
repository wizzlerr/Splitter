package com.ootb.db.friends.dao;

import com.ootb.db.user.type.User;

/**
 * Created by Adam on 2017-04-02.
 */
public class FriendsFilter {

    private Long friendshipId;
    private User user;
    private User friend;
    private boolean confirmed;

    public Long getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Long friendshipId) {
        this.friendshipId = friendshipId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public static final class FriendsFilterBuilder {
        private Long friendshipId;
        private User user;
        private User friend;
        private boolean confirmed;

        private FriendsFilterBuilder() {
        }

        public static FriendsFilterBuilder aFriendsFilter() {
            return new FriendsFilterBuilder();
        }

        public FriendsFilterBuilder withFriendshipId(Long friendshipId) {
            this.friendshipId = friendshipId;
            return this;
        }

        public FriendsFilterBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public FriendsFilterBuilder withFriend(User friend) {
            this.friend = friend;
            return this;
        }

        public FriendsFilterBuilder withConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
            return this;
        }

        public FriendsFilter build() {
            FriendsFilter friendsFilter = new FriendsFilter();
            friendsFilter.setFriendshipId(friendshipId);
            friendsFilter.setUser(user);
            friendsFilter.setFriend(friend);
            friendsFilter.setConfirmed(confirmed);
            return friendsFilter;
        }
    }
}
