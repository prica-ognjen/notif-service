package com.badpc.notif.dto;

public class PasswordResetNotificationDto {

    private Long id;
    private String email;
    private Type type = Type.NOTIFICATION_RESET_PASSWORD;

    public PasswordResetNotificationDto() {
    }

    public PasswordResetNotificationDto(Long id, Type type) {
        this.id = id;
        this.type = type;
    }

    public PasswordResetNotificationDto(Long id, String email, Type type) {
        this.id = id;
        this.email = email;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
