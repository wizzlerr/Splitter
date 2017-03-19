package com.ootb.web.security.login;

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "security/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String onLogin() {

        return "/";
    }

    @RequestMapping("/login-error")
    public String loginError(@RequestParam(value = "error", required = false) String error, Model model) {

        model.addAttribute("loginError", true);
        return "security/login";
    }

}
