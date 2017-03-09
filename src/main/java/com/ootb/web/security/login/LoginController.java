package com.ootb.web.security.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Adam on 2017-01-26.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "security/login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {

        model.addAttribute("loginError", true);
        return "security/login";
    }

}
