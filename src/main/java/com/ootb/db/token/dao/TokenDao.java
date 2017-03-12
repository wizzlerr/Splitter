package com.ootb.db.token.dao;

import com.ootb.db.token.type.VerificationToken;
import com.ootb.db.token.type.VerificationTokenFilter;
import com.ootb.db.user.type.User;
import com.ootb.db.util.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.ootb.db.token.type.VerificationTokenFilter.VerificationTokenFilterBuilder.aVerificationTokenFilter;

/**
 * Created by Adam on 2017-03-11.
 */
@Component
public class TokenDao extends AbstractDao {

    public void save(VerificationToken myToken) {
        persist(myToken);
    }

    public VerificationToken findByToken(String token) {
        VerificationTokenFilter verificationTokenFilter = aVerificationTokenFilter().withToken(token).build();
        List<VerificationToken> tokens = findByFilter(verificationTokenFilter);
        return !tokens.isEmpty() ? tokens.get(0) : null;
    }

    public VerificationToken findByUser(User user) {
        VerificationTokenFilter verificationTokenFilter = aVerificationTokenFilter().withUserId(user).build();
        List<VerificationToken> tokens = findByFilter(verificationTokenFilter);
        return !tokens.isEmpty() ? tokens.get(0) : null;
    }

    public List<VerificationToken> findByFilter(VerificationTokenFilter verificationTokenFilter) {
        Criteria criteria = sessionFactory.openSession().createCriteria(VerificationToken.class);

        if(verificationTokenFilter.getId()!=null){
            criteria.add(Expression.eq("id",verificationTokenFilter.getId()));
        }
        if(verificationTokenFilter.getExpiryDate()!=null){
            criteria.add(Expression.eq("expiryDate",verificationTokenFilter.getExpiryDate()));
        }
        if(verificationTokenFilter.getToken()!=null){
            criteria.add(Expression.eq("token",verificationTokenFilter.getToken()));
        }
        if(verificationTokenFilter.getUser()!=null){
            criteria.add(Expression.eq("user",verificationTokenFilter.getUser()));
        }
        return criteria.list();
    }
}
