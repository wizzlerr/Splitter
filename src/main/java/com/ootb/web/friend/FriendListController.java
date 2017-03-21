package com.ootb.web.friend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Adam on 2017-03-19.
 */
@Controller
@RequestMapping(value = "/auth")
public class FriendListController {

    @RequestMapping(value = "/friends")
    public String friendList() {
        return "index";
    }

}
