package org.software_assignment.lms.controller;

import org.software_assignment.lms.entity.NotificationEntity;
import org.software_assignment.lms.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {
    final NotificationService notificationService = new NotificationService();

    @GetMapping("/api/notification/get/{user_id}")
    public List<NotificationEntity> getNotification(@PathVariable("user_id") Integer userId){
        return notificationService.getNotifications(userId);
    }
    @PostMapping("/api/notification/add/{user_id}")
    void addNotification(@PathVariable("user_id") Integer userId, @RequestBody NotificationEntity notificationEntity){
        notificationService.pushNotification(notificationEntity, userId);
    }

}
