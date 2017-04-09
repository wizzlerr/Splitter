package com.ootb.db.friends.type;

import com.ootb.db.user.type.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Adam on 2017-04-02.
 */
@Entity
@Table(name = "friend")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = User.class)
    @JoinColumn
    private User firstUser;

    @OneToOne(targetEntity = User.class)
    @JoinColumn
    private User secondUser;

    @Column(name = "confirmed")
    private boolean confirmed;

    public Friend() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public static final class FriendBuilder {
        private Long id;
        private User firstUser;
        private User secondUser;
        private boolean confirmed;

        private FriendBuilder() {
        }

        public static FriendBuilder aFriend() {
            return new FriendBuilder();
        }

        public FriendBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public FriendBuilder withFirstUser(User firstUser) {
            this.firstUser = firstUser;
            return this;
        }

        public FriendBuilder withSecondUser(User secondUser) {
            this.secondUser = secondUser;
            return this;
        }

        public FriendBuilder withConfirmed(boolean confirmed) {
            this.confirmed= confirmed;
            return this;
        }

        public Friend build() {
            Friend friend = new Friend();
            friend.setId(id);
            friend.setFirstUser(firstUser);
            friend.setSecondUser(secondUser);
            friend.setConfirmed(confirmed);
            return friend;
        }
    }
}
