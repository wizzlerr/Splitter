package com.ootb.service.security.token;

import com.ootb.db.token.dao.TokenDao;
import com.ootb.db.token.type.VerificationToken;
import com.ootb.db.user.type.User;
import com.ootb.service.infotainment.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

import static com.ootb.db.token.type.VerificationToken.VerificationTokenBuilder.aVerificationToken;

/**
 * Created by Adam on 2017-03-11.
 */
@Service
public class TokenService {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private TokenDao tokenDao;

    public String getRandomToken() {
        return UUID.randomUUID().toString();
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = aVerificationToken().withToken(token).withUser(user).build();
        tokenDao.save(myToken);
    }

    public boolean handleBadRegistraionToken(VerificationToken verificationToken) {
        if (verificationToken == null) {
            notificationService.addDangerMessage("Nieprawidłowy token rejestracji");
            return true;
        }
        return false;
    }

    public boolean hasExpired(VerificationToken verificationToken) {
        Calendar cal = Calendar.getInstance();
        return (verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0;
    }

    public boolean handleExpiredRegistrationToken(VerificationToken verificationToken) {
        if (hasExpired(verificationToken)) {
            notificationService.addDangerMessage("Token przeterminowany");
            return true;
        }
        return false;
    }

    public VerificationToken getVerificationToken(String token) {
        return tokenDao.findByToken(token);
    }

    public boolean userHasRegistrationToken(User user) {
        return tokenDao.findByUser(user) != null;
    }

    public void deleteToken(VerificationToken verificationToken) {
        tokenDao.deleteToken(verificationToken);
    }
}
