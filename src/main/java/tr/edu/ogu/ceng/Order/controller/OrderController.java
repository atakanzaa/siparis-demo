package tr.edu.ogu.ceng.Order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.ogu.ceng.Order.Service.OrderService;
import tr.edu.ogu.ceng.Order.entity.Order;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
@RestController

public class OrderController {


    private final OrderService orderService;

    // Sipariş oluşturma
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    // Belirli bir siparişi ID'ye göre alma
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.findOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Tüm siparişleri listeleme
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Sipariş silme
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    // Sipariş durumu güncelleme
    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        Optional<Order> updatedOrder = orderService.updateOrderStatus(id, status);
        return updatedOrder.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Kullanıcının siparişlerini listeleme
    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<Order>> getOrdersByUserId(@PathVariable Long userId) {
        Optional<Order> orders = orderService.findOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    // Sipariş onaylama
    @PostMapping("/{id}/confirm")
    public ResponseEntity<Order> confirmOrder(@PathVariable Long id) {
        Optional<Order> confirmedOrder = orderService.confirmOrder(id);
        return confirmedOrder.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
