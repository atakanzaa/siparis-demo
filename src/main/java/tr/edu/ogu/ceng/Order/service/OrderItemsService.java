package tr.edu.ogu.ceng.Order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.Order.entity.Order_Items;
import tr.edu.ogu.ceng.Order.repository.OrderItemsRepository;


@Service
@RequiredArgsConstructor

public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    public Order_Items saveOrderItems(Order_Items orderItems ){
        return orderItemsRepository.save(orderItems );
    }
}