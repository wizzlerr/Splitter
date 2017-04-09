package com.ootb.web.profile;

import com.ootb.service.profile.user.ProfileService;
import com.ootb.web.technical.stereotype.AuthRequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Adam on 2017-04-04.
 */
@Controller
@AuthRequestMapping
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/profile/{name}")
    public String profile(@PathVariable("name") String name, Model model) {
        model.addAttribute("profile", profileService.getProfile(name));
        return "profile/profile";
    }

    @RequestMapping(value = "/profile")
    public String profile(Model model) {
        model.addAttribute("profile", profileService.getProfile());
        return "profile/profile";
    }

    @RequestMapping(value = "/profile/{name}/add")
    public String profileAdd(@PathVariable("name") String name, Model model) {
        return "profile/profile";
    }
}
