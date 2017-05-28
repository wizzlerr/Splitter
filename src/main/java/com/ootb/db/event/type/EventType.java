package com.ootb.db.event.type;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Adam on 2017-04-09.
 */
public enum EventType {

    NEW_FRIEND_INVITATION, NEW_GROUP_INVITATION,
    NEW_FRIEND_EXPENSE, EDITED_FRIEND_EXPENSE, DELETED_FRIEND_EXPENSE,
    NEW_GROUP_EXPENSE, EDITED_GROUP_EXPENSE, DELETED_GROUP_EXPENSE;

    public static List<EventType> getIntivationEvents() {
        return Arrays.asList(NEW_FRIEND_INVITATION, NEW_GROUP_INVITATION);
    }
}
