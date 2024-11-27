package tr.edu.ogu.ceng.Order.RepositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Order.entity.Payment;
import tr.edu.ogu.ceng.Order.repository.PaymentRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PaymentRepositoryTest {

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:16-alpine"));

    static {
        postgres.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private PaymentRepository repository;

    @Test
    public void testSaveAndFindByStatus() {
        Payment payment = new Payment();
        payment.setAmount(500.0);
        payment.setStatus("Completed");

        repository.save(payment);

        Payment foundPayment = (Payment) repository.findByStatus("Completed").orElseThrow();
        assertThat(foundPayment.getAmount()).isEqualTo(500.0);
    }

    @Test
    public void testDeletePayment() {
        Payment payment = new Payment();
        payment.setAmount(200.0);
        payment.setStatus("Pending");

        Payment savedPayment = repository.save(payment);
        repository.delete(savedPayment);

        assertThat(repository.findById(savedPayment.getPaymentId())).isEmpty();
    }
}
