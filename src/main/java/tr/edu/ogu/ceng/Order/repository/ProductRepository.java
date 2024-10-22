package tr.edu.ogu.ceng.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Order.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}