package com.ootb.db.util;

import com.ootb.service.logger.type.InjectLogger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
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

    protected void  delete(Object object) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(session.contains(object) ? object : session.merge(object));
        tx.commit();
        session.flush();
        session.close();

        LOGGER.info("Deleted " + object.toString());
    }

    protected boolean deleteById(Class<?> type, Serializable id) {
        Session session = sessionFactory.openSession();
        Object persistentInstance = session.load(type, id);
        if (persistentInstance != null) {
            session.delete(persistentInstance);
            session.flush();
            session.close();

            LOGGER.info("Deleted " + persistentInstance.toString());
            return true;
        }
        session.flush();
        session.close();
        return false;
    }

    protected void delete(Class<?> type, Long id) {
        Session session = sessionFactory.openSession();
        Object object = session.load(type, id);
        Transaction tx = session.beginTransaction();
        session.delete(object);
        tx.commit();
        session.close();

        LOGGER.info("Deleted " + object.toString());
    }

    protected void queryDelete(String clazz, Long id) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("delete " + clazz +" where id = :delId");
        query.setParameter("delId", id);

        query.executeUpdate();
    }
}
