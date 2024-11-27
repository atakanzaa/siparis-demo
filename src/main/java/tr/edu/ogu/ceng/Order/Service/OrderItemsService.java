package tr.edu.ogu.ceng.Order.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.Order.entity.Order_Items;
import tr.edu.ogu.ceng.Order.repository.OrderItemsRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    public Order_Items saveOrderItems(Order_Items orderItems ){
        return orderItemsRepository.save(orderItems );
    }

    public Optional<Order_Items> getOrderItemById(Long id) {
        return orderItemsRepository.findById(id);
    }
    public List<Order_Items> getAllOrderItems() {
        return orderItemsRepository.findAll();
    }
    public Order_Items updateOrderItem(Long id, Order_Items updatedOrderItem) {
        return orderItemsRepository.findById(id).map(orderItem -> {
            orderItem.setQuantity(updatedOrderItem.getQuantity());
            orderItem.setPrice(updatedOrderItem.getPrice());
            orderItem.setUpdatedAt(java.time.LocalDateTime.now());
            return orderItemsRepository.save(orderItem);
        }).orElseThrow(() -> new RuntimeException("Order Item not found"));
    }
    public void deleteOrderItem(Long id) {
        orderItemsRepository.deleteById(id);
    }
}