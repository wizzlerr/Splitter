package com.ootb.service.infotainment.email;

import com.ootb.db.user.type.User;
import com.ootb.service.event.type.OnRegistrationCompleteEvent;
import com.ootb.service.infotainment.email.type.Email;
import com.ootb.service.infotainment.email.type.TemplateEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import static com.ootb.service.infotainment.email.type.Email.EmailBuilder.anEmail;

/**
 * Created by Adam on 2017-03-11.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    public void sendRegistrationEmail(User user, String token, OnRegistrationCompleteEvent event) {

        String confirmationUrl
                = "/registrationConfirm?token=" + token;
        String message = "Zarejestrowałeś się pomyślnie, kliknij w link aby aktywować konto";
        String text = message + " " + "http://localhost:8080" + confirmationUrl;
        sendEmail(anEmail().withRecipentAddress(user.getEmail()).withSubject("Potwierdzenie rejestracji").withMessage(text).build());
    }

    public void sendRegistrationEmail(User user, String token) {

        String confirmationUrl
                = "/registrationConfirm?token=" + token;
        String message = "Zarejestrowałeś się pomyślnie, kliknij w link aby aktywować konto";

        TemplateEmail templateEmail = new TemplateEmail();
        templateEmail.setLink("http://localhost:8080" + confirmationUrl);
        templateEmail.setLinkText("Potwierdź rejestracje");
        templateEmail.setMessage(message);
        templateEmail.setUser(user);
        templateEmail.setSecondaryMessage("Miłego korzystania z serwisu!");

        sendEmail(templateEmail);
    }

    private void sendEmail(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email.getRecipentAddress());
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getMessage());
        javaMailSender.send(simpleMailMessage);
    }
//TODO TEMPLATE EMAIL
    private void sendEmail(final TemplateEmail email) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("Splitter Team");
            messageHelper.setTo(email.getRecipentAddress());
            messageHelper.setSubject(email.getSubject());

            String content = mailContentBuilder.build(email);
            messageHelper.setText(content, true);
        };
        try {
            javaMailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }




}