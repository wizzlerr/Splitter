package com.ootb.web.security.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Adam on 2017-01-26.
 */
@Controller
public class LoginController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
