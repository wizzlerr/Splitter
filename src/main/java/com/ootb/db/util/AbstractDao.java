package com.ootb.db.util;

import com.ootb.service.logger.type.InjectLogger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Adam on 2017-03-09.
 */
public abstract class AbstractDao {

    @Autowired
    protected SessionFactory sessionFactory;

    protected static @InjectLogger
    Logger LOGGER;

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

        LOGGER.info("Persisted " + object.toString());
    }

    protected void update(Object object) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(object);
        session.getTransaction().commit();
        session.close();

        LOGGER.info("Updated " + object.toString());
    }

    protected void delete(Object object) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(session.contains(object) ? object : session.merge(object));
        session.flush();
        session.getTransaction().commit();
        session.close();

        LOGGER.info("Deleted " + object.toString());
    }
}
