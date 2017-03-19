package com.ootb.service.user.type;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Adam on 2017-03-19.
 */
public class Authority implements GrantedAuthority {

    public String getAuthority() {
        return "USER";
    }
}
