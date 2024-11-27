package tr.edu.ogu.ceng.Order.RepositoryTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Order.entity.Customer;
import tr.edu.ogu.ceng.Order.repository.CustomerRepository;

@SpringBootTest
public class CustomerRepositoryTest {

    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            DockerImageName.parse("postgres:16-alpine"));

    static {
        postgres.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private CustomerRepository repository;

    @Test
    public void testSaveKullanici() {
        Customer customer = new Customer();
        customer.setName("Ahmet"); // Örnek alan
        customer.setEmail("ahmet@example.com"); // Örnek alan
        Customer savedCustomer = repository.save(customer);
        assert savedCustomer.getCustomerId() != null; // ID doğrulama
    }
}
