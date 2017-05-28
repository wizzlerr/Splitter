package com.ootb.db.event.type;

import com.ootb.db.user.type.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Adam on 2017-04-09.
 */
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_event", joinColumns = {
            @JoinColumn(name = "eventId", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id",
                    nullable = false, updatable = false) })
    private List<User> users;

    @Column(name = "eventType")
    private String eventType;

    @Column(name = "jsonEventObjectBefore")
    private String jsonEventObjectBefore;

    @Column(name = "jsonEventObjectAfter")
    private String jsonEventObjectAfter;

    public Event() {
    }

    public Long getId() {
        return eventId;
    }

    public void setId(Long id) {
        this.eventId = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getJsonEventObjectBefore() {
        return jsonEventObjectBefore;
    }

    public void setJsonEventObjectBefore(String jsonEventObjectBefore) {
        this.jsonEventObjectBefore = jsonEventObjectBefore;
    }

    public String getJsonEventObjectAfter() {
        return jsonEventObjectAfter;
    }

    public void setJsonEventObjectAfter(String jsonEventObjectAfter) {
        this.jsonEventObjectAfter = jsonEventObjectAfter;
    }


    public static final class EventBuilder {
        private List<User> users;
        private String eventType;
        private String jsonEventObjectBefore;
        private String jsonEventObjectAfter;

        private EventBuilder() {
        }

        public static EventBuilder anEvent() {
            return new EventBuilder();
        }

        public EventBuilder withUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public EventBuilder withEventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public EventBuilder withJsonEventObjectBefore(String jsonEventObjectBefore) {
            this.jsonEventObjectBefore = jsonEventObjectBefore;
            return this;
        }

        public EventBuilder withJsonEventObjectAfter(String jsonEventObjectAfter) {
            this.jsonEventObjectAfter = jsonEventObjectAfter;
            return this;
        }

        public Event build() {
            Event event = new Event();
            event.setUsers(users);
            event.setEventType(eventType);
            event.setJsonEventObjectBefore(jsonEventObjectBefore);
            event.setJsonEventObjectAfter(jsonEventObjectAfter);
            return event;
        }
    }
}
