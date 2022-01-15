package com.badpc.notif.service.impl;

import com.badpc.notif.domain.NotificationType;
import com.badpc.notif.dto.NotificationTypeCreateDto;
import com.badpc.notif.dto.NotificationTypeUpdateDto;
import com.badpc.notif.mapper.NotificationTypeMapper;
import com.badpc.notif.repository.NotificationTypeRepository;
import com.badpc.notif.service.NotificationTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationTypeServiceImpl implements NotificationTypeService {

    private final NotificationTypeRepository notificationTypeRepository;
    private final NotificationTypeMapper notificationTypeMapper;

    public NotificationTypeServiceImpl(NotificationTypeRepository notificationTypeRepository, NotificationTypeMapper notificationTypeMapper) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    @Override
    public void save(NotificationType t1) {
        notificationTypeRepository.save(t1);
    }

    @Override
    public ResponseEntity<List<NotificationType>> getAll() {
        return new ResponseEntity<>(notificationTypeRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NotificationType> create(NotificationTypeCreateDto notificationTypeCreateDto) {
        NotificationType notificationType = notificationTypeMapper.notificationTypeCreateDtoToNotificationType(notificationTypeCreateDto);

        return new ResponseEntity<>(notificationTypeRepository.save(notificationType), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Void> update(NotificationTypeUpdateDto notificationTypeUpdateDto, Long id) {
        NotificationType notificationType = notificationTypeRepository.getOne(id);

        if(!notificationTypeUpdateDto.getName().equals("")){
            notificationType.setName(notificationTypeUpdateDto.getName());
        }

        notificationTypeRepository.save(notificationType);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<NotificationType> delete(Long id) {
        notificationTypeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
