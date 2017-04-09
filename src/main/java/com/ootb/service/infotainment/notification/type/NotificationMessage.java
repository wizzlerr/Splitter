package com.ootb.service.infotainment.notification.type;

import java.util.Arrays;

/**
 * Created by Adam on 2017-03-11.
 */
public class NotificationMessage {
    private NotificationMessageType type;
    private String text;
    private String cssClass;
    private String strongText;

    public NotificationMessage(NotificationMessageType type, String text) {
        this.type = type;
        this.text = text;
        this.cssClass = "alert-" + type.name().toLowerCase();
        this.strongText = getStrongTextByMssgType() + "!";
    }

    private String getStrongTextByMssgType() {
        return Arrays.asList(NotificationMessageStrongText.values()).get(Arrays.asList(NotificationMessageType.values()).indexOf(type)).name();
    }

    public NotificationMessageType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getCssClass() {
        return cssClass;
    }

    public String getStrongText() {
        return strongText;
    }
}
