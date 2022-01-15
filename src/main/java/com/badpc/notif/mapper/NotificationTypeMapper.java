package com.badpc.notif.mapper;

import com.badpc.notif.domain.NotificationType;
import com.badpc.notif.dto.NotificationTypeCreateDto;

import org.springframework.stereotype.Component;

@Component
public class NotificationTypeMapper {
    public NotificationType notificationTypeCreateDtoToNotificationType(NotificationTypeCreateDto notificationTypeCreateDto) {
        NotificationType notificationType = new NotificationType();
        notificationType.setName(notificationTypeCreateDto.getName());
        return notificationType;

    }
}
