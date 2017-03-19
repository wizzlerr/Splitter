package com.ootb.service.user.type;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Adam on 2017-03-19.
 */
public class UserDetail implements UserDetails {

    private String password;
    private String userName;
    private boolean isEnabled;

    public UserDetail(String password, String userName, boolean isEnabled) {
        this.password = password;
        this.userName = userName;
        this.isEnabled = isEnabled;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>(Arrays.asList(new Authority()));
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return userName;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
