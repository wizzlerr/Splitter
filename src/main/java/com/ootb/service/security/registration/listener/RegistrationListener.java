package com.ootb.service.security.registration.listener;

import com.ootb.service.event.type.OnRegistrationCompleteEvent;
import com.ootb.service.security.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by Adam on 2017-03-11.
 */
@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private RegistrationService registrationService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        registrationService.handleRegistrationEvent(event);
    }


}
