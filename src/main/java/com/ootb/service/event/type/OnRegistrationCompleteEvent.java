package com.ootb.service.event.type;

import com.ootb.db.user.type.User;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Created by Adam on 2017-03-11.
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private User user;

    private OnRegistrationCompleteEvent(User user) {
        super(user);
        this.user = user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static final class OnRegistrationCompleteEventBuilder {
        private String appUrl;
        private Locale locale;
        private User user;

        private OnRegistrationCompleteEventBuilder() {
        }

        public static OnRegistrationCompleteEventBuilder anOnRegistrationCompleteEvent(User user) {
            return new OnRegistrationCompleteEventBuilder().withUser(user);
        }

        public OnRegistrationCompleteEventBuilder withAppUrl(String appUrl) {
            this.appUrl = appUrl;
            return this;
        }

        public OnRegistrationCompleteEventBuilder withLocale(Locale locale) {
            this.locale = locale;
            return this;
        }

        private OnRegistrationCompleteEventBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public OnRegistrationCompleteEvent build() {
            OnRegistrationCompleteEvent onRegistrationCompleteEvent = new OnRegistrationCompleteEvent(user);
            onRegistrationCompleteEvent.setAppUrl(appUrl);
            onRegistrationCompleteEvent.setLocale(locale);
            return onRegistrationCompleteEvent;
        }
    }
}
