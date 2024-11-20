package tr.edu.ogu.ceng.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Order.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String tablet);
}