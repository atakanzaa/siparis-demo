    package tr.edu.ogu.ceng.Order.Service;

    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestClient;
    import tr.edu.ogu.ceng.Order.entity.Notification;


    import java.time.LocalDateTime;

    @Service
    @RequiredArgsConstructor
    public class NotificationService {

        private final RestClient restClient;

        public void sendNotification(String recipient, String message, Notification.NotificationType type) {
            Notification notification = new Notification();
            notification.setRecipient(recipient);
            notification.setMessage(message);
            notification.setType(type);
            notification.setCreatedAt(LocalDateTime.now());

            // Bildirimi Notification mikroservisine gönderiyoruz
            restClient.post()
                    .uri("http://localhost:8083/api/v1/notifications")
                    .body(notification)
                    .retrieve()
                    .toBodilessEntity();
        }
    }
