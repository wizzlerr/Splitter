package com.ootb.web.security.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Adam on 2017-03-09.
 */
@Controller
public class ErrorController {

    @RequestMapping("/403")
    public String error403() {
        return "error/403";
    }

}
