package com.badpc.notif.repository;

import com.badpc.notif.domain.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
    @Query("select t from NotificationType t where t.name = ?1")
    NotificationType getByName(String resSuccessClient);
}
