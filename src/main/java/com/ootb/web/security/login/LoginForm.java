package com.ootb.web.security.login;

import javax.validation.constraints.NotNull;

/**
 * Created by Adam on 2017-03-09.
 */
public class LoginForm {

    @NotNull
    private String userName;

    @NotNull
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
