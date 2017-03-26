package com.ootb.service.infotainment.email;

import com.ootb.service.infotainment.email.type.TemplateEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Created by Adam on 2017-03-26.
 */
@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(TemplateEmail templateEmail) {
        Context context = new Context();
        context.setVariable("message", templateEmail.getMessage());
        context.setVariable("username", templateEmail.getUser().getUserName());
        context.setVariable("link", templateEmail.getLink());
        context.setVariable("linkText", templateEmail.getLinkText());
        context.setVariable("secondaryMessage", templateEmail.getSecondaryMessage());
        return templateEngine.process("email-template", context);
    }

}