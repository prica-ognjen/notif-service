package com.badpc.notif.dto;

public class NotificationTypeUpdateDto {

    private String name;
    private String text;

    public NotificationTypeUpdateDto(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public NotificationTypeUpdateDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
