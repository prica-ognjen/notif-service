package com.badpc.notif.controller;

import com.badpc.notif.domain.NotificationType;
import com.badpc.notif.dto.NotificationTypeCreateDto;
import com.badpc.notif.dto.NotificationTypeUpdateDto;
import com.badpc.notif.security.CheckSecurity;
import com.badpc.notif.service.NotificationTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notificationTypes")
public class NotificationTypeController {

    private final NotificationTypeService notificationTypeService;

    public NotificationTypeController(NotificationTypeService notificationTypeService) {
        this.notificationTypeService = notificationTypeService;
    }

    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<NotificationType>> getNotificationTypes(
            @RequestHeader("Authorization") String authorization){
        return notificationTypeService.getAll();
    }

    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<NotificationType> createNotificationType(
            @RequestHeader("Authorization") String authorization,
            @RequestBody NotificationTypeCreateDto notificationTypeCreateDto){
        return notificationTypeService.create(notificationTypeCreateDto);
    }

    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNotificationType(
            @RequestHeader("Authorization") String authorization,
            @RequestBody NotificationTypeUpdateDto reNotificationTypeUpdateDto,
            @PathVariable("id") Long id){
        return notificationTypeService.update(reNotificationTypeUpdateDto, id);
    }

    @CheckSecurity(roles = {"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<NotificationType> deleteNotificationType(
            @RequestHeader("Authorization") String authorization,
            @PathVariable("id") Long id){
        return notificationTypeService.delete(id);
    }

}
