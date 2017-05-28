package com.ootb.service.invites.type;

/**
 * Created by Adam on 2017-04-17.
 */
public class Invite {
    private String inviteType;
    private String inviteDefinition;
    private String inviteAction;

    public String getInviteType() {
        return inviteType;
    }

    public void setInviteType(String inviteType) {
        this.inviteType = inviteType;
    }

    public String getInviteDefinition() {
        return inviteDefinition;
    }

    public void setInviteDefinition(String inviteDefinition) {
        this.inviteDefinition = inviteDefinition;
    }

    public String getInviteAction() {
        return inviteAction;
    }

    public void setInviteAction(String inviteAction) {
        this.inviteAction = inviteAction;
    }
}