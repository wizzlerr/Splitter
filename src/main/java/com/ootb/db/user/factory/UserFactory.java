package com.ootb.db.user.factory;

import com.ootb.db.user.type.User;
import com.ootb.db.user.type.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

import static com.ootb.db.user.type.User.UserBuilder.anUser;

/**
 * Created by Adam on 2017-03-11.
 */
@Component
public class UserFactory {

    @Autowired
    private UserRoleFactory userRoleFactory;

    public User getNewlyRegisteredUser(String email, String userName, String password) {
        User user = anUser().withEmail(email).withUsername(userName).withPassword(password).withEnabled(false).build();
        HashSet<UserRole> userRoles = new HashSet<UserRole>();
        userRoles.add(userRoleFactory.getNewlyRegisteredRole(user));
        user.setUserRole(userRoles);
        return user;
    }
}
