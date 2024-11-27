package tr.edu.ogu.ceng.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.edu.ogu.ceng.Order.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String tablet);

    //  Ürün adına göre arama (isimde geçen kelimeler için LIKE kullanımı)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchByName(@Param("name") String name);

    //  Stokta olan ürünleri fiyatlarına göre sırala (en yüksekten en düşüğe)
    @Query("SELECT p FROM Product p WHERE p.stock > 0 ORDER BY p.price DESC")
    List<Product> findAvailableProductsSortedByPrice();

    // 10. Belirli bir ID ile ürünü getiren özel metod
    @Query("SELECT p FROM Product p WHERE p.productId = :id")
    Optional<Product> findProductById(@Param("id") Long id);
}