package com.ootb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Adam on 2017-03-09.
 */
@Controller
public class AppController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
