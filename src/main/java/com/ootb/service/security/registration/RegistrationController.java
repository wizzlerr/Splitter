package com.ootb.service.security.registration;

import com.ootb.db.token.type.VerificationToken;
import com.ootb.db.user.type.User;
import com.ootb.service.infotainment.notification.NotificationService;
import com.ootb.service.security.token.TokenService;
import com.ootb.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Adam on 2017-03-11.
 */
@Controller
public class RegistrationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration
            (@RequestParam("token") String token) {

        VerificationToken verificationToken = tokenService.getVerificationToken(token);
        if (tokenService.handleBadRegistraionToken(verificationToken)) {
            return "/";
        }

        User user = verificationToken.getUser();

        if (tokenService.handleExpiredRegistrationToken(verificationToken)) {
            return "/";
        }

        registrationService.completeRegistration(user);
        tokenService.deleteToken(verificationToken);
        return "security/login";
    }
}
