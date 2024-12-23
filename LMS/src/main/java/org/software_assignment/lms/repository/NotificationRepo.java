package org.software_assignment.lms.repository;

import org.software_assignment.lms.entity.NotificationEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NotificationRepo {

    private final Map<Integer, ArrayList<NotificationEntity>> notifications = new HashMap<>();

    public List<NotificationEntity> getNotifications(int id) {
        return notifications.get(id);
    }

    public void pushNotification(int id, NotificationEntity notification) {
        if (!notifications.containsKey(id)) {
            notifications.put(id, new ArrayList<>());
            notifications.get(id).add(notification);
        }
        notifications.get(id).add(notification);

        // Iterate over all notifications and print them
        for (Map.Entry<Integer, ArrayList<NotificationEntity>> entry : notifications.entrySet()) {
            System.out.println("User ID: " + entry.getKey());
            for (NotificationEntity notif : entry.getValue()) {
                System.out.println(" - " + notif.toString());
            }
        }
    }
}
