package com.ootb.service.security.registration;

import com.ootb.db.user.dao.UsersDao;
import com.ootb.db.user.factory.UserFactory;
import com.ootb.db.user.type.User;
import com.ootb.service.infotainment.email.EmailService;
import com.ootb.service.infotainment.notification.NotificationService;
import com.ootb.service.security.password.PasswordService;
import com.ootb.service.user.UserService;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2017-03-09.
 */
@Service
public class RegistrationService {

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EmailService emailService;

    public void registerNewUser(String email, String userName, String password) {
        if(userForRegistrationValid(email, userName)) {
            User newUser = userFactory.getNewlyRegisteredUser(email, userName, passwordService.encryptPassword(password));

        }
    }

    private boolean userForRegistrationValid(String email, String userName) {
        return isUserNameAvaiable(userName) && isEmailAvaiable(email) && isEmailValid(email);
    }

    private boolean isUserNameAvaiable(String userName) {
        if(userService.isUserNameAvaiable(userName)) {
            notificationService.addDangerMessage("Nazwa użytkoniwka zajęta");
            return false;
        }
        return true;
    }

    private boolean isEmailAvaiable(String email) {
        if(userService.isUserNameAvaiable(email)) {
            notificationService.addDangerMessage("Email zajęty");
            return false;
        }
        return true;
    }

    private boolean isEmailValid(String email) {
        if(!EmailValidator.getInstance().isValid(email)) {
            notificationService.addDangerMessage("Email nieprawidłowy");
            return false;
        }
        return true;
    }
}
