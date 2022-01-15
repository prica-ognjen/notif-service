package com.badpc.notif.listener;

import com.badpc.notif.listener.helper.MessageHelper;
import com.badpc.notif.repository.NotificationTypeRepository;
import com.badpc.notif.service.EmailService;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEmailListener {

    private final MessageHelper messageHelper;
    private final EmailService emailService;
    private final NotificationTypeRepository notificationTypeRepository;

    public UserCreateEmailListener(MessageHelper messageHelper, EmailService emailService, NotificationTypeRepository notificationTypeRepository) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationTypeRepository = notificationTypeRepository;
    }


}
