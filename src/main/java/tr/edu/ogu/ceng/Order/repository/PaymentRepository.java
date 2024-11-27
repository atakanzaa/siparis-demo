package tr.edu.ogu.ceng.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.edu.ogu.ceng.Order.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Object> findByStatus(String completed);

    //  Belirli bir miktardan büyük ödemeleri bul
    @Query("SELECT p FROM Payment p WHERE p.amount > ?1")
    List<Payment> findPaymentsWithAmountGreaterThan(Double amount);

    //  En son yapılan 10 ödemeyi listele
    @Query("SELECT p FROM Payment p ORDER BY p.paymentDate DESC")
    List<Payment> findTop10ByLatestPayments();
}
