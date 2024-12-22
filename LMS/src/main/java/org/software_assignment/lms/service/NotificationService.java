package org.software_assignment.lms.service;

import org.software_assignment.lms.entity.NotificationEntity;
import org.software_assignment.lms.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    final NotificationRepo notificationRepo = new NotificationRepo();

    public  List<NotificationEntity> getNotifications(Integer id)
    {
        return notificationRepo.getNotifications(id);
    }

    public  void pushNotification(NotificationEntity notificationEntity , Integer id)
    {
        notificationRepo.pushNotification(id, notificationEntity);
    }
}
