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
import tr.edu.ogu.ceng.Order.entity.Order_Items;
import tr.edu.ogu.ceng.Order.repository.OrderItemsRepository;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderItemsRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private OrderItemsRepository repository;

    @Test
    public void testSaveAndFindById() {
        Order_Items orderItem = new Order_Items();
        orderItem.setProductId(1L);
        orderItem.setQuantity(3);
        orderItem.setPrice(150.0);

        Order_Items savedOrderItem = repository.save(orderItem);
        assertThat(savedOrderItem.getOrderItemId()).isNotNull();

        Order_Items foundOrderItem = repository.findById(savedOrderItem.getOrderItemId()).orElseThrow();
        assertThat(foundOrderItem.getPrice()).isEqualTo(150.0);
    }

    @Test
    public void testDelete() {
        Order_Items orderItem = new Order_Items();
        orderItem.setProductId(2L);
        orderItem.setQuantity(5);
        orderItem.setPrice(300.0);

        Order_Items savedOrderItem = repository.save(orderItem);
        repository.delete(savedOrderItem);

        assertThat(repository.findById(savedOrderItem.getOrderItemId())).isEmpty();
    }
}
