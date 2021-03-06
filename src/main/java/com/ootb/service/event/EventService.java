package com.ootb.service.event;

import com.ootb.db.event.dao.EventDao;
import com.ootb.db.event.dao.EventFilter;
import com.ootb.db.event.type.Event;
import com.ootb.db.event.type.EventType;
import com.ootb.db.user.type.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ootb.db.event.dao.EventFilter.EventFilterBuilder.anEventFilter;
import static com.ootb.db.event.type.Event.EventBuilder.anEvent;
import static com.ootb.db.event.type.EventType.getIntivationEvents;

/**
 * Created by Adam on 2017-04-17.
 */
@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    public void addFriendInvitationEvent(User friend) {
        List<User> users = new ArrayList<>();
        users.add(friend);
        Event event = anEvent().withEventType(EventType.NEW_FRIEND_INVITATION.name()).withUsers(users).build();
        eventDao.update(event);
    }

    public List<Event> getInviteEventsByUser(User user) {
        List<User> users = new ArrayList<>();
        users.add(user);
        EventFilter eventFilter = anEventFilter().withUsers(users).withEventType(getIntivationEvents()).build();
        return eventDao.findAllByFilter(eventFilter);
    }
}
