package tr.edu.ogu.ceng.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}