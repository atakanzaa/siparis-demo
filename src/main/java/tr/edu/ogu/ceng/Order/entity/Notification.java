package tr.edu.ogu.ceng.Order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notification {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipient;
    private String message;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private NotificationType type;



    public enum NotificationType {
        PAYMENT_SUCCESS,
        PAYMENT_FAILED
    }
}
