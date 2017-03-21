package com.ootb.web.security.login;

import com.ootb.service.infotainment.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Adam on 2017-01-26.
 */
@Controller
public class LoginController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "security/login";
    }

    @RequestMapping("/login-error")
    public String loginError(@RequestParam(value = "error", required = false) String error, Model model) {

        notificationService.addWarningMessage("Niepoprawna nazwa użytkownika lub hasło");
        return "security/login";
    }

}
