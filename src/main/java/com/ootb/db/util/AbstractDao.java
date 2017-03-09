package com.ootb.db.util;

import org.hibernate.Criteria;
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

        return criteria.list();
    }

}
