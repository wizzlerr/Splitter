package com.ootb.service.security.registration;

import com.ootb.db.user.factory.UserFactory;
import com.ootb.db.user.type.User;
import com.ootb.service.event.type.OnRegistrationCompleteEvent;
import com.ootb.service.infotainment.email.EmailService;
import com.ootb.service.infotainment.notification.NotificationService;
import com.ootb.service.security.password.PasswordService;
import com.ootb.service.security.token.TokenService;
import com.ootb.service.user.UserService;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

import static com.ootb.service.event.type.OnRegistrationCompleteEvent.OnRegistrationCompleteEventBuilder.anOnRegistrationCompleteEvent;

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

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void registerNewUser(String email, String userName, String password, HttpServletRequest request) {
        if(userForRegistrationValid(email, userName)) {
            register(email, userName, password, request);
            notificationService.addSuccessMessage("Zostałeś zarejestrowwany. Sprawdź email i potwierdź swoją rejestracje!");
        }
    }

    private void register(String email, String userName, String password, HttpServletRequest request) {
        User newUser = userFactory.getNewlyRegisteredUser(email, userName, passwordService.encryptPassword(password));
        userService.addUser(newUser);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eventPublisher.publishEvent(anOnRegistrationCompleteEvent(newUser).withAppUrl(request.getRequestURI()).withLocale(request.getLocale()).build());
    }

    public void handleRegistrationEvent(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = tokenService.getRandomToken();
        tokenService.createVerificationToken(user, token);

        emailService.sendRegistrationEmail(user, token, event);
    }

    private boolean userForRegistrationValid(String email, String userName) {
        return isUserNameAvaiable(userName) && isEmailAvaiable(email) && isEmailValid(email);
    }

    private boolean isUserNameAvaiable(String userName) {
        if(!userService.isUserNameAvaiable(userName)) {
            notificationService.addDangerMessage("Nazwa użytkoniwka zajęta");
            return false;
        }
        return true;
    }

    private boolean isEmailAvaiable(String email) {
        if(!userService.isEmailAvaiable(email)) {
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
