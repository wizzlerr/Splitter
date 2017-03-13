package com.ootb.web.security.register;

import com.ootb.service.security.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Adam on 2017-03-09.
 */
@Controller
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "security/register";
    }

    @RequestMapping("/register/new")
    public String register(@ModelAttribute @Valid RegisterForm registerForm, HttpServletRequest request) {
        registrationService.registerNewUser(registerForm.getEmail(), registerForm.getUserName(), registerForm.getPassword(), request);
        return "redirect:/login";
    }
}
