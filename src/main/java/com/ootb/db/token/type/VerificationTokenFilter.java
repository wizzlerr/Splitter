package com.ootb.db.token.type;

import com.ootb.db.user.type.User;

import java.util.Date;

/**
 * Created by Adam on 2017-03-12.
 */
public class VerificationTokenFilter {

    private Long id;

    private String token;

    private User user;

    private Date expiryDate;

    public VerificationTokenFilter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    public static final class VerificationTokenFilterBuilder {
        private Long id;
        private String token;
        private User userId;
        private Date expiryDate;

        private VerificationTokenFilterBuilder() {
        }

        public static VerificationTokenFilterBuilder aVerificationTokenFilter() {
            return new VerificationTokenFilterBuilder();
        }

        public VerificationTokenFilterBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public VerificationTokenFilterBuilder withToken(String token) {
            this.token = token;
            return this;
        }

        public VerificationTokenFilterBuilder withUserId(User userId) {
            this.userId = userId;
            return this;
        }

        public VerificationTokenFilterBuilder withExpiryDate(Date expiryDate) {
            this.expiryDate = expiryDate;
            return this;
        }

        public VerificationTokenFilter build() {
            VerificationTokenFilter verificationTokenFilter = new VerificationTokenFilter();
            verificationTokenFilter.setId(id);
            verificationTokenFilter.setToken(token);
            verificationTokenFilter.setUser(userId);
            verificationTokenFilter.setExpiryDate(expiryDate);
            return verificationTokenFilter;
        }
    }
}
