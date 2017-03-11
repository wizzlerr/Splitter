package com.ootb.service.security.password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2017-03-11.
 */
@Service
public class PasswordService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
