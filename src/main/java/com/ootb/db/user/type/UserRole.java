package com.ootb.db.user.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Adam on 2017-03-09.
 */
@Entity
@Table(name = "user_roles",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "role", "userId" }))
public class UserRole{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    public UserRole() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static final class UserRoleBuilder {
        private Integer id;
        private User user;
        private String role;

        private UserRoleBuilder() {
        }

        public static UserRoleBuilder anUserRole() {
            return new UserRoleBuilder();
        }

        public UserRoleBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public UserRoleBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public UserRoleBuilder withRole(String role) {
            this.role = role;
            return this;
        }

        public UserRole build() {
            UserRole userRole = new UserRole();
            userRole.setId(id);
            userRole.setUser(user);
            userRole.setRole(role);
            return userRole;
        }
    }
}
