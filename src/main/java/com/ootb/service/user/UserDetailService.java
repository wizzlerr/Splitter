package com.ootb.service.user;

import com.ootb.db.user.dao.UsersDao;
import com.ootb.db.user.type.User;
import com.ootb.service.user.type.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Adam on 2017-03-19.
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UsersDao usersDao;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = usersDao.findByUserName(userName);

        if(user != null) {
           return new UserDetail(user.getPassword(), user.getUserName(), user.isEnabled());
        }
        return null;
    }
}
