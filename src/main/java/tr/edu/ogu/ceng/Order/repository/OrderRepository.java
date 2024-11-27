package tr.edu.ogu.ceng.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Order.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Belirli bir versiyon numarasına sahip siparişleri bul
    List<Order> findByVersion(Integer version);

    //  Belirli bir tarih aralığındaki siparişleri bul
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    //  Belirli bir durumdaki siparişleri bul
    List<Order> findByStatus(String status);

}