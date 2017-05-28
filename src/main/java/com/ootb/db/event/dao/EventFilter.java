package com.ootb.db.event.dao;

import com.ootb.db.event.type.EventType;
import com.ootb.db.user.type.User;

import java.util.List;

/**
 * Created by Adam on 2017-04-16.
 */
public class EventFilter {

    private Long id;

    private List<User> users;

    private List<EventType> eventType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<EventType> getEventType() {
        return eventType;
    }

    public void setEventType(List<EventType> eventType) {
        this.eventType = eventType;
    }

    public static final class EventFilterBuilder {
        private Long id;
        private List<User> users;
        private List<EventType> eventType;

        private EventFilterBuilder() {
        }

        public static EventFilterBuilder anEventFilter() {
            return new EventFilterBuilder();
        }

        public EventFilterBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public EventFilterBuilder withUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public EventFilterBuilder withEventType(List<EventType> eventType) {
            this.eventType = eventType;
            return this;
        }

        public EventFilter build() {
            EventFilter eventFilter = new EventFilter();
            eventFilter.setId(id);
            eventFilter.setUsers(users);
            eventFilter.setEventType(eventType);
            return eventFilter;
        }
    }
}
