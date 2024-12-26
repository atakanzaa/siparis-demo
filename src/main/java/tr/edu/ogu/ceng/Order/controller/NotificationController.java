package tr.edu.ogu.ceng.Order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.ogu.ceng.Order.Service.NotificationService;
import tr.edu.ogu.ceng.Order.dto.NotificationRequestDto;
import tr.edu.ogu.ceng.Order.entity.Notification;
import tr.edu.ogu.ceng.Order.entity.Notification.NotificationType;

@RequestMapping("/api/v1/notifications")
@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // Notification API'yi REST üzerinden alır
    @PostMapping("/body")
    public ResponseEntity<String> sendNotificationWithBody(@RequestBody NotificationRequestDto notificationRequestDto) {
        notificationService.sendNotification(
                notificationRequestDto.getRecipient(),
                notificationRequestDto.getMessage(),
                NotificationType.valueOf(String.valueOf(notificationRequestDto.getType()))  // Enum tipi burada kullanılır
        );
        return ResponseEntity.ok("Notification sent successfully to " + notificationRequestDto.getRecipient());
    }
}
