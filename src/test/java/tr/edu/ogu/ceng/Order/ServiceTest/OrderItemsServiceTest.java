package tr.edu.ogu.ceng.Order.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Order.AbstractContainerBaseTest;
import tr.edu.ogu.ceng.Order.Service.OrderItemsService;
import tr.edu.ogu.ceng.Order.entity.Order_Items;
import tr.edu.ogu.ceng.Order.repository.OrderItemsRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderItemsServiceTest extends AbstractContainerBaseTest {

    @Autowired
    private OrderItemsService orderItemsService;

    @MockBean
    private OrderItemsRepository orderItemsRepository;

    private Order_Items orderItem;

    @BeforeEach
    void setUp() {
        orderItem = new Order_Items();
        orderItem.setOrderItemId(1L);
        orderItem.setQuantity(2);
        orderItem.setPrice(50.0);
        orderItem.setCreatedAt(java.time.LocalDateTime.now());
    }

    @Test
    void testSaveOrderItem() {
        when(orderItemsRepository.save(Mockito.any(Order_Items.class))).thenReturn(orderItem);

        Order_Items savedOrderItem = orderItemsService.saveOrderItems(orderItem);

        assertThat(savedOrderItem).isNotNull();
        verify(orderItemsRepository, times(1)).save(orderItem);
    }

    @Test
    void testGetOrderItemById() {
        when(orderItemsRepository.findById(orderItem.getOrderItemId())).thenReturn(Optional.of(orderItem));

        Optional<Order_Items> fetchedOrderItem = orderItemsService.getOrderItemById(orderItem.getOrderItemId());

        assertThat(fetchedOrderItem).isPresent();
        assertThat(fetchedOrderItem.get().getOrderItemId()).isEqualTo(orderItem.getOrderItemId());
        verify(orderItemsRepository, times(1)).findById(orderItem.getOrderItemId());
    }

    @Test
    void testUpdateOrderItem() {
        Order_Items updatedOrderItem = new Order_Items();
        updatedOrderItem.setQuantity(3);
        updatedOrderItem.setPrice(60.0);

        when(orderItemsRepository.findById(orderItem.getOrderItemId())).thenReturn(Optional.of(orderItem));
        when(orderItemsRepository.save(Mockito.any(Order_Items.class))).thenReturn(orderItem);

        Order_Items result = orderItemsService.updateOrderItem(orderItem.getOrderItemId(), updatedOrderItem);

        assertThat(result.getQuantity()).isEqualTo(updatedOrderItem.getQuantity());
        assertThat(result.getPrice()).isEqualTo(updatedOrderItem.getPrice());
        verify(orderItemsRepository, times(1)).findById(orderItem.getOrderItemId());
        verify(orderItemsRepository, times(1)).save(orderItem);
    }

    @Test
    void testDeleteOrderItem() {
        doNothing().when(orderItemsRepository).deleteById(orderItem.getOrderItemId());

        orderItemsService.deleteOrderItem(orderItem.getOrderItemId());

        verify(orderItemsRepository, times(1)).deleteById(orderItem.getOrderItemId());
    }
}
