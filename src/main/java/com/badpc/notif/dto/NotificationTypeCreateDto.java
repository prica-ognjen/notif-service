package com.badpc.notif.dto;

public class NotificationTypeCreateDto {

    private String name;
    private String text;

    public String getName() {
        return name;
    }

    public NotificationTypeCreateDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setName(String name) {
        this.name = name;
    }
}
