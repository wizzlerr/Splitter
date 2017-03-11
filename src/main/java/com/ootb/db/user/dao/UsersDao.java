package com.ootb.db.user.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import com.ootb.db.user.type.User;
import com.ootb.db.util.AbstractDao;

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
            users.get(0);
        }
        return null;
    }

    public List<User> findAllByFilter(UserFilter userFilter) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);

        if(userFilter.getEmail()!=null){
            criteria.add(Expression.ge("email",userFilter.getEmail()));
        }
        if(userFilter.getId()!=null){
            criteria.add(Expression.le("id",userFilter.getId()));
        }
        if(userFilter.getUserName()!=null){
            criteria.add(Expression.ge("userName",userFilter.getUserName()));
        }

        return criteria.list();
    }

    public List<User> findAll() {
        return (List<User>) find(User.class);
    }

    public void addUser(User user) {
        persist(user);
    }
}
