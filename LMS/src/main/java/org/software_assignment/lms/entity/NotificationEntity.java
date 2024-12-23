package org.software_assignment.lms.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

public class NotificationEntity {
    @Getter
    @Setter
    LocalDate date;
    String message;
    public NotificationEntity(LocalDate date, String message) {
        this.date = date;
        this.message = message;
    }
}
