package com.ootb.db.token.dao;

import com.ootb.db.token.type.VerificationToken;
import com.ootb.db.util.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Adam on 2017-03-11.
 */
@Component
public class TokenDao extends AbstractDao {

    public void save(VerificationToken myToken) {
        persist(myToken);
    }

    public VerificationToken findByToken(String token) {
        Criteria criteria = sessionFactory.openSession().createCriteria(VerificationToken.class);
        criteria.add(Expression.eq("token",token));

        List<VerificationToken> tokens = criteria.list();
         return tokens != null ? tokens.get(0) : null;
    }
}
