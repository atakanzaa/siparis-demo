package tr.edu.ogu.ceng.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Order.entity.Payment;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Object> findByStatus(String completed);
}
