package tr.edu.ogu.ceng.Order.dto;

import lombok.Data;
import tr.edu.ogu.ceng.Order.entity.Notification;

@Data
public class NotificationRequestDto {
    private String recipient;
    private String message;
    private Notification.NotificationType type;
}
