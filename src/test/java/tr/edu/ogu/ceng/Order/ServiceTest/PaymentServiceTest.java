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






}
