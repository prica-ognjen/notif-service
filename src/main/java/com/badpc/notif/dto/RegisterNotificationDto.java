package com.badpc.notif.dto;

public class RegisterNotificationDto {

    private final Type notificationType = Type.NOTIFICATION_REGISTER;
    private String firstName;
    private String lastName;
    private String email;
    private Long id;

    public RegisterNotificationDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public RegisterNotificationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getNotificationType() {
        return notificationType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
