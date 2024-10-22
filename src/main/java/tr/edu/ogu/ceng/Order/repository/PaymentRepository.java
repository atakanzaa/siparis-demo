package tr.edu.ogu.ceng.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Order.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
