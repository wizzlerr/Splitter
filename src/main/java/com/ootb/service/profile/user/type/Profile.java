package com.ootb.service.profile.user.type;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Adam on 2017-04-04.
 */
public class Profile {

    private String nick;
    private BigDecimal ownedToYou;
    private BigDecimal youOwe;
    private BigDecimal personalExpense;
    private BigDecimal balance;
    private boolean isFriend;
    private boolean isYou;

    public String getPersonalExpenseDisplay() {
        return personalExpense.setScale(2, RoundingMode.HALF_EVEN).toString() + " PLN";
    }

    public String getOwnedToYouDisplay() {
        return ownedToYou.setScale(2, RoundingMode.HALF_EVEN).toString() + " PLN";
    }

    public String getYouOweDisplay() {
        return youOwe.setScale(2, RoundingMode.HALF_EVEN).toString() + " PLN";
    }

    public String getBalanceDisplay() {
        return balance.setScale(2, RoundingMode.HALF_EVEN).toString() + " PLN";
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public BigDecimal getOwnedToYou() {
        return ownedToYou;
    }

    public void setOwnedToYou(BigDecimal ownedToYou) {
        this.ownedToYou = ownedToYou;
    }

    public BigDecimal getYouOwe() {
        return youOwe;
    }

    public void setYouOwe(BigDecimal youOwe) {
        this.youOwe = youOwe;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPersonalExpense() {
        return personalExpense;
    }

    public void setPersonalExpense(BigDecimal personalExpense) {
        this.personalExpense = personalExpense;
    }

    public boolean getIsFriend() {
        return isFriend;
    }

    public void setFriend(boolean friend) {
        isFriend = friend;
    }

    public boolean getIsYou() {
        return isYou;
    }

    public void setYou(boolean you) {
        isYou = you;
    }

    public static final class ProfileBuilder {
        private String nick;
        private BigDecimal ownedToYou;
        private BigDecimal youOwe;
        private BigDecimal personalExpense;
        private BigDecimal balance;
        private boolean isFriend;
        private boolean isYou;

        private ProfileBuilder() {
        }

        public static ProfileBuilder aProfile() {
            return new ProfileBuilder();
        }

        public ProfileBuilder withNick(String nick) {
            this.nick = nick;
            return this;
        }

        public ProfileBuilder withOwnedToYou(BigDecimal ownedToYou) {
            this.ownedToYou = ownedToYou;
            return this;
        }

        public ProfileBuilder withYouOwe(BigDecimal youOwe) {
            this.youOwe = youOwe;
            return this;
        }

        public ProfileBuilder withPersonalExpense(BigDecimal personalExpense) {
            this.personalExpense = personalExpense;
            return this;
        }

        public ProfileBuilder withBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public ProfileBuilder withIsFriend(boolean isFriend) {
            this.isFriend = isFriend;
            return this;
        }

        public ProfileBuilder withIsYou(boolean isYou) {
            this.isYou = isYou;
            return this;
        }

        public Profile build() {
            Profile profile = new Profile();
            profile.setNick(nick);
            profile.setOwnedToYou(ownedToYou);
            profile.setYouOwe(youOwe);
            profile.setPersonalExpense(personalExpense);
            profile.setBalance(balance);
            profile.isYou = this.isYou;
            profile.isFriend = this.isFriend;
            return profile;
        }
    }
}
