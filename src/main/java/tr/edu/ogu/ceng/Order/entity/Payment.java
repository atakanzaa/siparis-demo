package tr.edu.ogu.ceng.Order.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(nullable = false)
    private LocalDateTime paymentDate;

    @Column(nullable = false)
    private Double amount;

    @Column( nullable = false, length = 50)
    private String paymentMethod;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;

    @Column
    private String createdBy;

    @Column
    private String updatedBy;

    @Column
    private String deletedBy;

    @Column
    private Integer version;

    public void setAmount(double v) {
        amount = v;
    }

    public double getAmount() {
        return amount;
    }

    public void setOrderId(long l) {
        paymentId = l;
    }

    public long getOrderId() {
        return paymentId;
    }

    public void setPaymentTimestamp(LocalDateTime now) {
        paymentDate = now;
    }

    public Object getPaymentTimestamp() {
        return paymentDate;
    }
}
