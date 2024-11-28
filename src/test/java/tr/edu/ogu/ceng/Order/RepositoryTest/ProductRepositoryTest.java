package tr.edu.ogu.ceng.Order.RepositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Order.AbstractContainerBaseTest;
import tr.edu.ogu.ceng.Order.entity.Product;
import tr.edu.ogu.ceng.Order.repository.ProductRepository;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void testSaveAndFindByName() {
        Product product = new Product();
        product.setName("Tablet");
        product.setPrice(2500.0);
        repository.save(product);

        List<Product> foundUrunler = repository.findByName("Tablet");
        assertThat(foundUrunler).isNotEmpty();
        assertThat(foundUrunler.getFirst().getPrice()).isEqualTo(2500.0);
    }

    @Test
    public void testUpdatePrice() {
        Product product = new Product();
        product.setName("Klavye");
        product.setPrice(150.0);
        Product savedProduct = repository.save(product);

        savedProduct.setPrice(200.0);
        repository.save(savedProduct);

        Product updatedUrun = repository.findById(savedProduct.getProductId()).orElseThrow();
        assertThat(updatedUrun.getPrice()).isEqualTo(200.0);
    }
}
