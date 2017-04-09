package com.ootb.db.user.dao;

import com.ootb.db.util.AbstractFilter;

/**
 * Created by Adam on 2017-03-09.
 */
public class UserFilter extends AbstractFilter {

    private String userName;
    private String email;
    private String id;
    private boolean enabled;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public static final class UserFilterBuilder {
        private String userName;
        private String email;
        private String id;
        private boolean enabled;

        private UserFilterBuilder() {
        }

        public static UserFilterBuilder anUserFilter() {
            return new UserFilterBuilder();
        }

        public UserFilterBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserFilterBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserFilterBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public UserFilterBuilder withEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserFilter build() {
            UserFilter userFilter = new UserFilter();
            userFilter.setUserName(userName);
            userFilter.setEmail(email);
            userFilter.setId(id);
            userFilter.setEnabled(enabled);
            return userFilter;
        }
    }
}
