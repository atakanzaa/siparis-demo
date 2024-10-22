package tr.edu.ogu.ceng.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Order.entity.Order_Items;

public interface OrderItemsRepository extends JpaRepository<Order_Items, Long> {

}