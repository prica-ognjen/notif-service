package com.badpc.notif.service;

import com.badpc.notif.domain.NotificationType;
import com.badpc.notif.dto.NotificationTypeCreateDto;
import com.badpc.notif.dto.NotificationTypeUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotificationTypeService {

    void save(NotificationType t1);

    ResponseEntity<List<NotificationType>> getAll();

    ResponseEntity<NotificationType> create(NotificationTypeCreateDto notificationTypeCreateDto);

    ResponseEntity<Void> update(NotificationTypeUpdateDto reNotificationTypeUpdateDto, Long id);

    ResponseEntity<NotificationType> delete(Long id);
}
