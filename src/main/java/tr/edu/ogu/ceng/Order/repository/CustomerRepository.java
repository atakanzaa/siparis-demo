package tr.edu.ogu.ceng.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Order.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
