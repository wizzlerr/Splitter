package com.ootb.db.user.factory;

import com.ootb.db.user.type.User;
import com.ootb.db.user.type.UserRole;
import org.springframework.stereotype.Component;

import static com.ootb.db.user.type.UserRole.UserRoleBuilder.anUserRole;

/**
 * Created by Adam on 2017-03-11.
 */
@Component
public class UserRoleFactory {

    public UserRole getNewlyRegisteredRole(User user) {
        return anUserRole().withRole("USER").withUser(user).build();
    }
}
