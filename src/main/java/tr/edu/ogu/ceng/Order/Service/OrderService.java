package tr.edu.ogu.ceng.Order.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.Order.entity.Order;
import tr.edu.ogu.ceng.Order.repository.OrderRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class OrderService {

    private final OrderRepository orderRepository;

    // Sipariş kaydetme
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // ID ile sipariş bulma
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Tüm siparişleri listeleme
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    // ID ile sipariş silme
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Sipariş durumu güncelleme
    public Optional<Order> updateOrderStatus(Long id, String status) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            existingOrder.setStatus(status); // "status" alanı Order sınıfında tanımlı olmalı.
            orderRepository.save(existingOrder);
        }
        return order;
    }

    // Kullanıcının siparişlerini bulma
    public Optional<Order> findOrdersByUserId(Long userId) {
        return orderRepository.findById(userId); // Bu yöntem OrderRepository'de tanımlı olmalı.
    }

    // Sipariş onaylama
    public Optional<Order> confirmOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            Order existingOrder = order.get();
            existingOrder.setStatus("CONFIRMED"); // "CONFIRMED" sabit bir durum.
            orderRepository.save(existingOrder);
        }
        return order;
    }



}