package tr.edu.ogu.ceng.Order.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Order.AbstractContainerBaseTest;
import tr.edu.ogu.ceng.Order.Service.PaymentService;
import tr.edu.ogu.ceng.Order.entity.Payment;
import tr.edu.ogu.ceng.Order.repository.PaymentRepository;


import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PaymentServiceTest  extends AbstractContainerBaseTest {

    @MockBean
    private PaymentRepository paymentRepository; // Mock'lanan PaymentRepository

    @Autowired
    private PaymentService paymentService; // PaymentService'e otomatik olarak mock'lar injekte edilir

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
        payment.setAmount(100.0);
        payment.setPaymentMethod("Credit Card");
        payment.setStatus("Completed");
        payment.setPaymentDate(LocalDateTime.now());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setCreatedBy("system");
        payment.setVersion(1);
    }

    @Test
    void testCreatePayment() {
        // Payment nesnesini mock repository'den kaydedildiğini simüle et
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        // PaymentService'in createPayment metodunu test et
        Payment createdPayment = paymentService.createPayment(100.0, "Credit Card", "Completed");

        // Sonuçların beklenen değerlerle uyumlu olup olmadığını kontrol et
        assertNotNull(createdPayment);
        assertEquals(100.0, createdPayment.getAmount());
        assertEquals("Credit Card", createdPayment.getPaymentMethod());
        assertEquals("Completed", createdPayment.getStatus());
        assertNotNull(createdPayment.getPaymentDate());
        assertNotNull(createdPayment.getCreatedAt());
        assertEquals("system", createdPayment.getCreatedBy());

        // save() metodunun doğru bir şekilde çağrıldığını kontrol et
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testCreatePayment_invalidAmount() {
        // Amount 0 veya negatifse ödeme oluşturulamaz
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.createPayment(-100.0, "Credit Card", "Failed");
        });
    }


}
