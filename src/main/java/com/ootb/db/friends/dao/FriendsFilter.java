package com.ootb.db.friends.dao;

import com.ootb.db.user.type.User;

/**
 * Created by Adam on 2017-04-02.
 */
public class FriendsFilter {

    private Long friendshipId;
    private User user;

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


    public static final class FriendsFilterBuilder {
        private Long friendshipId;
        private User user;

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

        public FriendsFilter build() {
            FriendsFilter friendsFilter = new FriendsFilter();
            friendsFilter.setFriendshipId(friendshipId);
            friendsFilter.setUser(user);
            return friendsFilter;
        }
    }
}
