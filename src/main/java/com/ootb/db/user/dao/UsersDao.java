package com.ootb.db.user.dao;

import com.ootb.db.user.type.User;
import com.ootb.db.util.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ootb.db.user.dao.UserFilter.UserFilterBuilder.anUserFilter;

/**
 * Created by Adam on 2017-03-09.
 */
@Repository
public class UsersDao extends AbstractDao {

    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {
        return findByFilter(anUserFilter().withUserName(username).build());
    }

    public User findByEmail(String email) {
        return findByFilter(anUserFilter().withEmail(email).build());
    }

    public User findByFilter(UserFilter userFilter) {
        List<User> users = findAllByFilter(userFilter);
        if(!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public List<User> findAllByFilter(UserFilter userFilter) {
        Criteria criteria = getCriteria(User.class);

        if(userFilter.getEmail()!=null){
            criteria.add(Expression.eq("email",userFilter.getEmail()));
        }
        if(userFilter.getId()!=null){
            criteria.add(Expression.eq("id",userFilter.getId()));
        }
        if(userFilter.getUserName()!=null){
            criteria.add(Expression.eq("userName",userFilter.getUserName()));
        }
        if(userFilter.isEnabled()){
            criteria.add(Expression.eq("enabled",userFilter.isEnabled()));
        }
        return criteria.list();
    }

    public void addUser(User user) {
        persist(user);
    }

    public void updateUseer(User user) {
        update(user);
    }

    public List<User> findAll() {
        return (List<User>) find(User.class);
    }

    public List<User> findAllEnabled() {
        return findAllByFilter(anUserFilter().withEnabled(true).build());
    }
}
