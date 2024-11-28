package tr.edu.ogu.ceng.Order.RepositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Order.AbstractContainerBaseTest;
import tr.edu.ogu.ceng.Order.entity.Order;
import tr.edu.ogu.ceng.Order.repository.OrderRepository;

@SpringBootTest
public class OrderRepositoryTest extends AbstractContainerBaseTest {


    @Autowired
    private OrderRepository repository;

    @Test
    public void testSaveSiparis() {
        Order order = new Order();
        order.setStatus("Hazırlanıyor"); // Örnek alan
        order.setTotalAmount(250.0); // Örnek alan
        Order savedOrder = repository.save(order);
        assert savedOrder.getOrderId() != null; // ID doğrulama
    }
}
