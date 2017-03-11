package com.ootb.db.util;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Adam on 2017-03-09.
 */
public abstract class AbstractDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    protected List<?> find(Class clazz) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(clazz);
        sessionFactory.getCurrentSession().close();
        return criteria.list();
    }

    protected void persist(Object object) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(object);
        session.getTransaction().commit();
        session.close();
    }

}
