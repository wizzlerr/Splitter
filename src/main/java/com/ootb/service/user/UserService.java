package com.ootb.service.user;

import com.ootb.db.user.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2017-03-11.
 */
@Service
public class UserService {

    @Autowired
    private UsersDao usersDao;

    public boolean isUserNameAvaiable(String userName) {
        return usersDao.findByUserName(userName) != null;
    }

    public boolean isEmailAvaiable(String email) {
        return usersDao.findByEmail(email) != null;
    }
}
