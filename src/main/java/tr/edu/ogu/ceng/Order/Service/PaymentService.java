package tr.edu.ogu.ceng.Order.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tr.edu.ogu.ceng.Order.dto.PaymentDto;
import tr.edu.ogu.ceng.Order.entity.Order;
import tr.edu.ogu.ceng.Order.entity.Payment;
import tr.edu.ogu.ceng.Order.entity.Notification.NotificationType;
import tr.edu.ogu.ceng.Order.repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestClient restClient;
    private final NotificationService notificationService;
    private final OrderService orderService;

    // Create Payment
    public Payment createPayment(PaymentDto paymentDto) {
        // PaymentDto'yu Payment entity'sine dönüştürme
        Payment payment = convertToEntity(paymentDto);

        payment.setPaymentDate(LocalDateTime.now());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setCreatedBy("system");

        // Ödemeyi kaydetme
        Payment savedPayment = paymentRepository.save(payment);

        // Ödeme durumu kontrolü
        if (savedPayment.getStatus().equals("COMPLETED")) {
            // Sipariş onaylama
            orderService.confirmOrder(savedPayment.getOrderId());

            // Ödeme başarılı bildirim gönder
            notificationService.sendNotification(
                    "admin@example.com",  // Admin ya da ilgili kullanıcı
                    "Ödeme tamamlandı: " + savedPayment.getAmount(),
                    NotificationType.PAYMENT_SUCCESS
            );
        } else {
            // Ödeme alınamadı bildirim gönder
            notificationService.sendNotification(
                    "admin@example.com",  // Admin ya da ilgili kullanıcı
                    "Ödeme alınamadı: " + savedPayment.getAmount(),
                    NotificationType.PAYMENT_FAILED
            );
        }

        return savedPayment;
    }
    // DTO'dan Entity'ye dönüşüm metodu
    private Payment convertToEntity(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setStatus(paymentDto.getStatus());
        payment.setOrderId(paymentDto.getOrderId());  // Order ID'yi de alıyoruz
        payment.setPaymentDate(LocalDateTime.now());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setCreatedBy("system");
        payment.setVersion(1);  // İlk versiyon
        return payment;
    }

    // Update Payment
    public Payment updatePayment(Long id, PaymentDto payment) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);

        if (!existingPayment.isPresent()) {
            throw new RuntimeException("Payment not found");
        }

        Payment updatedPayment = existingPayment.get();
        updatedPayment.setAmount(payment.getAmount());
        updatedPayment.setPaymentMethod(payment.getPaymentMethod());
        updatedPayment.setStatus(payment.getStatus());
        updatedPayment.setUpdatedAt(LocalDateTime.now());
        updatedPayment.setUpdatedBy("system");

        return paymentRepository.save(updatedPayment);
    }

    // Delete Payment
    public void deletePayment(Long id) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);
        if (!existingPayment.isPresent()) {
            throw new RuntimeException("Payment not found");
        }

        paymentRepository.delete(existingPayment.get());
    }

    // Get Payment by ID
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    // Sipariş Onaylama
    private void confirmOrder(Long orderId) {
        Optional<Order> orderOptional = orderService.confirmOrder(orderId);
        if (!orderOptional.isPresent()) {
            throw new RuntimeException("Sipariş onaylanırken bir hata oluştu");
        }
    }
}
