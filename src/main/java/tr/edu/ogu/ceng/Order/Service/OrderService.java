package tr.edu.ogu.ceng.Order.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.Order.entity.Order;
import tr.edu.ogu.ceng.Order.repository.OrderRepository;


@Service
@RequiredArgsConstructor

public class OrderService {

    private final OrderRepository orderRepository;

    public Order saveOrder(Order order ){
        return orderRepository.save(order);
    }
}