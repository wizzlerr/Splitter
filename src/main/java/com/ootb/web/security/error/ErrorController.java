package com.ootb.web.security.error;

import com.ootb.web.security.error.form.ErrorForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.ootb.web.security.error.form.ErrorForm.ErrorFormBuilder.anErrorForm;

/**
 * Created by Adam on 2017-03-09.
 */
@Controller
public class ErrorController {


    @RequestMapping("/403")
    public String error403(Model model) {
        ErrorForm error403 = anErrorForm().withHeader("Błąd 403!").withMain("Wygląda na to, że nie masz praw aby dostać się do tej części strony.")
                .withHelper("Upewnij się, że masz uprawnienia i skontaktuj się z administraacją serwisu.").build();
        model.addAttribute("form", error403);
        return "/error/generic-error";
    }

    @RequestMapping("/access-denied")
    public String accessDenied(Model model) {
        ErrorForm error403 = anErrorForm().withHeader("Błąd!").withMain("Wygląda na to, że nie masz praw aby dostać się do tej części strony.")
                .withHelper("Upewnij się, że masz uprawnienia i skontaktuj się z administraacją serwisu.").build();
        model.addAttribute("form", error403);
        return "/error/generic-error";
    }

}
