package com.ootb.service.user;

import com.ootb.db.user.dao.UsersDao;
import com.ootb.db.user.type.User;
import com.ootb.service.infotainment.notification.NotificationService;
import com.ootb.service.user.type.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Adam on 2017-03-11.
 */
@Service
public class UserService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private NotificationService notificationService;

    public boolean isUserNameAvaiable(String userName) {
        return usersDao.findByUserName(userName) == null;
    }

    public boolean isEmailAvaiable(String email) {
        return usersDao.findByEmail(email) == null;
    }

    public void addUser(User user) {
        usersDao.addUser(user);
    }

    public void updateUser(User user) {
        usersDao.updateUseer(user);
    }

    public void enableUser(User user) {
        user.setEnabled(true);
        updateUser(user);
    }

    public User getLoggedUser() {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usersDao.findByUserName(userDetail.getUsername());
    }

    public List<User> getAllUsers() {
        return usersDao.findAll();
    }

    public List<User> getAllEnabledUsers() {
        return usersDao.findAllEnabled();
    }

    public List<User> getEnabledUsers() {
        List<User> users = getAllEnabledUsers();
        User loggedUser = getLoggedUser();
        users.removeIf(user -> user.getUserName().equals(loggedUser.getUserName()));
        return users;
    }

    public User getUser(String nick) {
        User user = usersDao.findByUserName(nick);
        if(user == null) {
            notificationService.addDangerMessage("Nie znaleziono użytkownika " + nick);
        }
        return user;
    }
}
