package tr.edu.ogu.ceng.Order.ServiceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Order.AbstractContainerBaseTest;
import tr.edu.ogu.ceng.Order.Service.CustomerService;
import tr.edu.ogu.ceng.Order.entity.Customer;
import tr.edu.ogu.ceng.Order.repository.CustomerRepository;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest

public class CustomerServiceTest extends AbstractContainerBaseTest {

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    void testSaveCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");

        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer savedCustomer = customerService.saveCustomer(customer);

        // Assert
        assertNotNull(savedCustomer);
        assertEquals("John Doe", savedCustomer.getName());
        assertEquals("john.doe@example.com", savedCustomer.getEmail());
        verify(customerRepository, times(1)).save(customer);
    }



}
