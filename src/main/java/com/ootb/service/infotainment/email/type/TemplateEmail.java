package com.ootb.service.infotainment.email.type;

import com.ootb.db.user.type.User;

/**
 * Created by Adam on 2017-03-26.
 */
public class TemplateEmail extends Email {

    private User user;
    private String link;
    private String linkText;
    private String secondaryMessage;
    private String secondaryLink;
    private String secondaryLinkText;

    public TemplateEmail() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getSecondaryMessage() {
        return secondaryMessage;
    }

    public void setSecondaryMessage(String secondaryMessage) {
        this.secondaryMessage = secondaryMessage;
    }

    public String getSecondaryLink() {
        return secondaryLink;
    }

    public void setSecondaryLink(String secondaryLink) {
        this.secondaryLink = secondaryLink;
    }

    public String getSecondaryLinkText() {
        return secondaryLinkText;
    }

    public void setSecondaryLinkText(String secondaryLinkText) {
        this.secondaryLinkText = secondaryLinkText;
    }


}
