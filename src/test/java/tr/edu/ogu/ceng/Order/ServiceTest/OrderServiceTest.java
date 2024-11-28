package tr.edu.ogu.ceng.Order.ServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Order.AbstractContainerBaseTest;
import tr.edu.ogu.ceng.Order.Service.OrderService;
import tr.edu.ogu.ceng.Order.entity.Order;
import tr.edu.ogu.ceng.Order.repository.OrderRepository;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest extends AbstractContainerBaseTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    private Order testOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testOrder = new Order();
        testOrder.setOrderId(1L);
        testOrder.setOrderDate(LocalDateTime.now());
        testOrder.setStatus("Pending");
        testOrder.setTotalAmount(150.00);
        testOrder.setCreatedAt(LocalDateTime.now());
    }

    @Test
    public void testSaveOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        Order savedOrder = orderService.saveOrder(testOrder);

        assertNotNull(savedOrder);
        assertEquals(testOrder.getOrderId(), savedOrder.getOrderId());
        assertEquals(testOrder.getStatus(), savedOrder.getStatus());
        verify(orderRepository, times(1)).save(testOrder);
    }

    @Test
    public void testFindOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));

        Optional<Order> retrievedOrder = orderService.findOrderById(1L);

        assertTrue(retrievedOrder.isPresent());
        assertEquals(testOrder.getOrderId(), retrievedOrder.get().getOrderId());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteOrder() {
        doNothing().when(orderRepository).deleteById(1L);

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).deleteById(1L);
    }
}
