package com.ootb.web.friend;

import com.ootb.web.technical.stereotype.AuthRequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Adam on 2017-03-19.
 */
@Controller
@AuthRequestMapping
public class FriendListController {

    @RequestMapping(value = "/friends")
    public String friendList() {
        return "index";
    }

}
