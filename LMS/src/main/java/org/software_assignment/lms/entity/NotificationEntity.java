package org.software_assignment.lms.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class NotificationEntity {
    @Getter
    @Setter
    Date date;
    String message;
    public NotificationEntity(Date date, String message) {
        this.date = date;
        this.message = message;
    }
}
