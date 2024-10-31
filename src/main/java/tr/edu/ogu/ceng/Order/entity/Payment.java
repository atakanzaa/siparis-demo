package tr.edu.ogu.ceng.Order.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "version")
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
