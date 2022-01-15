package com.badpc.notif.dto;

public class CancelReservationNotificationDto {

    private final Type notificationType = Type.NOTIFICATION_CANCEL_RESERVATION;
    private Long idReservation;
    private String email;

    public CancelReservationNotificationDto() {
    }

    public CancelReservationNotificationDto(Long idReservation, String email) {

        this.idReservation = idReservation;
        this.email = email;
    }

    public Type getNotificationType() {
        return notificationType;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
