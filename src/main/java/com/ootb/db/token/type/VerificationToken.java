package com.ootb.db.token.type;

import com.ootb.db.user.type.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Adam on 2017-03-11.
 */
@Entity
@Table(name = "token")
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user")
    private User user;

    @Column(name = "exipryDate")
    private Date expiryDate;

    public VerificationToken() {}

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

    private static Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }


    public static final class VerificationTokenBuilder {
        private String token;
        private User user;

        private VerificationTokenBuilder() {
        }

        public static VerificationTokenBuilder aVerificationToken() {
            return new VerificationTokenBuilder();
        }

        public VerificationTokenBuilder withToken(String token) {
            this.token = token;
            return this;
        }

        public VerificationTokenBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public VerificationToken build() {
            VerificationToken verificationToken = new VerificationToken();
            verificationToken.setToken(token);
            verificationToken.setUser(user);
            verificationToken.setExpiryDate(calculateExpiryDate(EXPIRATION));
            return verificationToken;
        }
    }
}
