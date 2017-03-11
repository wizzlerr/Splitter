package com.ootb.service.infotainment.email;

import com.ootb.db.user.type.User;
import com.ootb.service.event.type.OnRegistrationCompleteEvent;
import com.ootb.service.infotainment.email.type.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.ootb.service.infotainment.email.type.Email.EmailBuilder.anEmail;

/**
 * Created by Adam on 2017-03-11.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(User user, String token, OnRegistrationCompleteEvent event) {

        String confirmationUrl
                = event.getAppUrl() + "/regitrationConfirm?token=" + token;
        String message = "registrationSuccess";
        String text = message + " rn" + "http://localhost:8080" + confirmationUrl;
        sendEmail(anEmail().withRecipentAddress(user.getEmail()).withSubject("Potwierdzenie rejestracji").withMessage(text).build());
    }

    private void sendEmail(Email email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email.getRecipentAddress());
        simpleMailMessage.setSubject(email.getSubject());
        simpleMailMessage.setText(email.getMessage());
        mailSender.send(simpleMailMessage);
    }
}