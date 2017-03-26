package com.ootb.db.expenses.personal.dao;

import com.ootb.db.expenses.personal.type.PersonalExpense;
import com.ootb.db.user.type.User;
import com.ootb.db.util.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ootb.db.expenses.personal.dao.PersonalExpenseFilter.PersonalExpenseFilterBuilder.aPersonalExpenseFilter;

/**
 * Created by Adam on 2017-03-26.
 */
@Repository
public class PersonalExpenseDao extends AbstractDao {

    public List<PersonalExpense> findByUser(User user) {
        return findByFilter(aPersonalExpenseFilter().withUser(user).build());
    }

    public List<PersonalExpense> findByFilter(PersonalExpenseFilter personalExpenseFilter) {
         return findAllByFilter(personalExpenseFilter);
    }

    public List<PersonalExpense> findAllByFilter(PersonalExpenseFilter personalExpenseFilter) {
        Criteria criteria = sessionFactory.openSession().createCriteria(PersonalExpense.class);

        if(personalExpenseFilter.getCategory()!=null){
            criteria.add(Expression.eq("category",personalExpenseFilter.getCategory()));
        }
        if(personalExpenseFilter.getId()!=null){
            criteria.add(Expression.eq("id",personalExpenseFilter.getId()));
        }
        if(personalExpenseFilter.getCurrency()!=null){
            criteria.add(Expression.eq("currency",personalExpenseFilter.getCurrency()));
        }
        if(personalExpenseFilter.getDate()!=null){
            criteria.add(Expression.eq("date", personalExpenseFilter.getDate()));
        }
        if(personalExpenseFilter.getName()!=null){
            criteria.add(Expression.eq("name", personalExpenseFilter.getName()));
        }
        if(personalExpenseFilter.getUser()!=null){
            criteria.add(Expression.eq("user", personalExpenseFilter.getUser()));
        }
        return criteria.list();
    }

    public void savePersonalExpense(PersonalExpense personalExpense) {
        persist(personalExpense);
        LOGGER.info("Zapisano wydatek " + personalExpense.toString());
    }

    public PersonalExpense deleteById(Long id) {
        PersonalExpense personalExpense = findById(id);
        if(personalExpense != null) {
            delete(personalExpense);
            LOGGER.info("Usunięto wydatek " + personalExpense.toString());
            return personalExpense;
        }
        return null;
    }

    public PersonalExpense findById(Long id) {
        List<PersonalExpense> personalExpenses = findByFilter(aPersonalExpenseFilter().withId(id).build());
        if(personalExpenses.isEmpty()) {
            return null;
        }
        return personalExpenses.get(0);
    }

    public void update(PersonalExpense personalExpense) {
        super.update(personalExpense);
        LOGGER.info("Update wydatku " + personalExpense.toString());
    }
}
