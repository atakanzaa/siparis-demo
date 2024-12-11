package tr.edu.ogu.ceng.Order.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tr.edu.ogu.ceng.Order.dto.PaymentDto;
import tr.edu.ogu.ceng.Order.entity.Payment;
import tr.edu.ogu.ceng.Order.repository.PaymentRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestClient restClient;

    // Create Payment
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setStatus(paymentDto.getStatus());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setCreatedBy("system");
        payment.setVersion(1);

        Payment savedPayment = paymentRepository.save(payment);
        return convertToDto(savedPayment);
    }

    // Update Payment
    public PaymentDto updatePayment(Long id, PaymentDto paymentDto) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);

        if (!existingPayment.isPresent()) {
            throw new RuntimeException("Payment not found");
        }

        Payment payment = existingPayment.get();
        payment.setAmount(paymentDto.getAmount());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setStatus(paymentDto.getStatus());
        payment.setUpdatedAt(LocalDateTime.now());
        payment.setUpdatedBy("system");

        Payment updatedPayment = paymentRepository.save(payment);
        return convertToDto(updatedPayment);
    }

    // Delete Payment
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }

    // Get Payment by ID
    public PaymentDto getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return convertToDto(payment);
    }

    // Convert Payment to DTO
    private PaymentDto convertToDto(Payment payment) {
        PaymentDto dto = new PaymentDto();
        dto.setPaymentId(payment.getPaymentId());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setStatus(payment.getStatus());
        dto.setCreatedAt(payment.getCreatedAt());
        dto.setUpdatedAt(payment.getUpdatedAt());
        dto.setDeletedAt(payment.getDeletedAt());
        dto.setCreatedBy(payment.getCreatedBy());
        dto.setUpdatedBy(payment.getUpdatedBy());
        dto.setDeletedBy(payment.getDeletedBy());
        dto.setVersion(payment.getVersion());
        return dto;
    }
}
