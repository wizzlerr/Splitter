package com.ootb.service.friends.type;

/**
 * Created by Adam on 2017-04-02.
 */
public class Friend {

    private Long id;
    private String nick;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    public static final class FriendBuilder {
        private Long id;
        private String nick;

        private FriendBuilder() {
        }

        public static FriendBuilder aFriend() {
            return new FriendBuilder();
        }

        public FriendBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public FriendBuilder withNick(String nick) {
            this.nick = nick;
            return this;
        }

        public Friend build() {
            Friend friend = new Friend();
            friend.setId(id);
            friend.setNick(nick);
            return friend;
        }
    }
}
