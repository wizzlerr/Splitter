package com.ootb.service.infotainment.notification;

import com.ootb.service.infotainment.notification.type.NotificationMessage;
import com.ootb.service.infotainment.notification.type.NotificationMessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2017-03-11.
 */
@Service
public class NotificationService {
    public static final String NOTIFY_MSG_SESSION_KEY = "siteNotificationMessages";

    @Autowired
    private HttpSession httpSession;

    public void addInfoMessage(String msg) {
        addNotificationMessage(NotificationMessageType.INFO, msg);
    }

    public void addSuccessMessage(String msg) {
        addNotificationMessage(NotificationMessageType.SUCCESS, msg);
    }

    public void addWarningMessage(String msg) {
        addNotificationMessage(NotificationMessageType.WARNING, msg);
    }

    public void addDangerMessage(String msg) {
        addNotificationMessage(NotificationMessageType.DANGER, msg);
    }

    private void addNotificationMessage(NotificationMessageType type, String msg) {
        List<NotificationMessage> notifyMessages = (List<NotificationMessage>)
                httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);

        if (notifyMessages == null) {
            notifyMessages = new ArrayList<NotificationMessage>();
        }
        notifyMessages.add(new NotificationMessage(type, msg));
        httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, notifyMessages);
    }
}
