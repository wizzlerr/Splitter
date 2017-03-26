package com.ootb.service.security.registration;

import com.ootb.db.user.factory.UserFactory;
import com.ootb.db.user.type.User;
import com.ootb.service.event.type.OnRegistrationCompleteEvent;
import com.ootb.service.infotainment.email.EmailService;
import com.ootb.service.infotainment.notification.NotificationService;
import com.ootb.service.logger.type.InjectLogger;
import com.ootb.service.security.password.PasswordService;
import com.ootb.service.security.token.TokenService;
import com.ootb.service.user.UserService;
import org.apache.commons.validator.EmailValidator;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

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

    private static @InjectLogger Logger LOGGER;

    public void registerNewUser(String email, String userName, String password, HttpServletRequest request) {
        if(userForRegistrationValid(email, userName, password)) {
            register(email, userName, password, request);
            notificationService.addSuccessMessage("Zostałeś zarejestrowwany. Sprawdź email i potwierdź swoją rejestracje!");
        }
    }

    private void register(String email, String userName, String password, HttpServletRequest request) {
        User newUser = userFactory.getNewlyRegisteredUser(email, userName, passwordService.encryptPassword(password));
        userService.addUser(newUser);
        eventPublisher.publishEvent(anOnRegistrationCompleteEvent(newUser).withAppUrl(request.getRequestURI()).withLocale(request.getLocale()).build());
    }

    public void handleRegistrationEvent(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        if(!tokenService.userHasRegistrationToken(user)) {
            String token = tokenService.getRandomToken();
            tokenService.createVerificationToken(user, token);

            emailService.sendRegistrationEmail(user, token, event);
        } else {
            LOGGER.info("[PRZECHWYCIŁEM PODWÓJNY EVENT REJESTRACJI] Użytkownik ma już wydany token rejestracyjny.");
        }
    }

    public void completeRegistration(User user) {
        userService.enableUser(user);
        notificationService.addSuccessMessage("Pomyślnie potwierdzono rejstracje");
    }

    private boolean userForRegistrationValid(String email, String userName, String password) {
        return isUserNameAvaiable(userName) && isEmailAvaiable(email) && isEmailValid(email) && isPasswordCorrect(password);
    }

    private boolean isUserNameAvaiable(String userName) {
        if(!userService.isUserNameAvaiable(userName)) {
            notificationService.addDangerMessage("Nazwa użytkoniwka zajęta");
            return false;
        }
        return true;
    }

    private boolean isPasswordCorrect(String password) {
        if(password.length() < 5 || password.length() > 10) {
            notificationService.addDangerMessage("Hasło powinno mieć od 5 do 10 znaków");
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
