package com.ootb.db.event.dao;

import com.ootb.db.event.type.Event;
import com.ootb.db.user.type.User;
import com.ootb.db.util.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.ootb.db.event.dao.EventFilter.EventFilterBuilder.anEventFilter;

/**
 * Created by Adam on 2017-04-16.
 */
@Component
public class EventDao extends AbstractDao {

    public Event findByUsers(List<User> users) {
        return null;
    }

    public List<Event> findByUser(User user) {
        List<User> users = new ArrayList<>();
        users.add(user);
        return findAllByFilter(anEventFilter().withUsers(users).build());
    }

    public Event findById(Long id) {
        return null;
    }

    public List<Event> findAllByFilter(EventFilter eventFilter) {
        Criteria criteria = getCriteria(Event.class);

        if(eventFilter.getId()!=null){
            criteria.add(Expression.eq("eventId",eventFilter.getEventType()));
        }
        if(eventFilter.getEventType()!=null){
            criteria.add(Expression.eq("eventType",eventFilter.getEventType()));
        }
        if(eventFilter.getUsers()!=null){
            criteria.add(Expression.in("id",eventFilter.getUsers()));
        }
        return criteria.list();
    }

    public void save(Event event) {
        persist(event);
    }

    public void update(Event event) {
        super.update(event);
    }


}
