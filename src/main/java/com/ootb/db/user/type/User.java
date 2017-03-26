package com.ootb.db.user.type;

import com.ootb.db.expenses.personal.type.PersonalExpense;
import com.ootb.db.token.type.VerificationToken;
import org.hibernate.validator.constraints.Email;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static com.ootb.db.user.type.UserRole.UserRoleBuilder.anUserRole;

/**
 * Created by Adam on 2017-03-09.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "userName", unique = true,
            nullable = false, length = 45)
    private String userName;

    @Column(name = "email", unique = true,
            nullable = false)
    @Email
    private String email;

    @Column(name = "password",
            nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private VerificationToken verificationToken;

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    private PersonalExpense personalExpense;

    public User() {
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRole() {
        return this.userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VerificationToken getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(VerificationToken verificationToken) {
        this.verificationToken = verificationToken;
    }

    public PersonalExpense getPersonalExpense() {
        return personalExpense;
    }

    public void setPersonalExpense(PersonalExpense personalExpense) {
        this.personalExpense = personalExpense;
    }

    private void setDefaultRole() {
        userRole.add(anUserRole().withRole("USER").withUser(this).build());
    }

    public static final class UserBuilder {
        private Integer id;
        private String username;
        private String email;
        private String password;
        private boolean enabled;
        private Set<UserRole> userRole = new HashSet<UserRole>(0);

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public UserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserBuilder withUserRole(Set<UserRole> userRole) {
            this.userRole = userRole;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setUserName(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setEnabled(enabled);
            user.setUserRole(userRole);
            user.setDefaultRole();
            return user;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", userRole=" + userRole +
                ", verificationToken=" + verificationToken +
                '}';
    }
}
