package com.ootb.web.security.register;

import com.ootb.db.user.dao.UsersDao;
import com.ootb.db.user.type.User;
import com.ootb.service.security.registration.RegistrationService;
import com.ootb.web.security.register.form.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.ootb.db.user.type.User.UserBuilder.anUser;

/**
 * Created by Adam on 2017-03-09.
 */
@Controller
public class RegisterController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UsersDao usersDao;

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        createUsers();
        return "security/register";
    }

    @RequestMapping("/register/new")
    public String register(@ModelAttribute @Valid RegisterForm registerForm, HttpServletRequest request) {
        registrationService.registerNewUser(registerForm.getEmail(), registerForm.getUserName(), registerForm.getPassword(), request);
        return "redirect:/login";
    }

    //TODO
    private void createUsers() {
        User u1 = anUser().withEmail("s@s.pl").withEnabled(true).withPassword("admin").withUsername("admin").build();
        User u2 = anUser().withEmail("s1@s.pl").withEnabled(true).withPassword("admin").withUsername("admin1").build();
        User u3 = anUser().withEmail("s2@s.pl").withEnabled(true).withPassword("admin").withUsername("admin2").build();
        User u4 = anUser().withEmail("s3@s.pl").withEnabled(true).withPassword("admin").withUsername("admin3").build();
        User u5 = anUser().withEmail("s4@s.pl").withEnabled(true).withPassword("admin").withUsername("admin4").build();
        User u6 = anUser().withEmail("s5@s.pl").withEnabled(true).withPassword("admin").withUsername("admin5").build();
        User u7 = anUser().withEmail("s6@s.pl").withEnabled(true).withPassword("admin").withUsername("admin6").build();

        usersDao.addUser(u1);
        usersDao.addUser(u2);
        usersDao.addUser(u3);
        usersDao.addUser(u4);
        usersDao.addUser(u5);
        usersDao.addUser(u6);
        usersDao.addUser(u7);
    }
}
