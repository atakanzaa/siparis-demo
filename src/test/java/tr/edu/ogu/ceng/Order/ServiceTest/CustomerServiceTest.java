package tr.edu.ogu.ceng.Order.ServiceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tr.edu.ogu.ceng.Order.Service.CustomerService;
import tr.edu.ogu.ceng.Order.entity.Customer;
import tr.edu.ogu.ceng.Order.repository.CustomerRepository;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class CustomerServiceTest {

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

    @Test
    void testFindCustomerById() {
        // Arrange
        Long customerId = 1L;
        Customer mockCustomer = new Customer();
        mockCustomer.setCustomerId(customerId);
        mockCustomer.setName("Jane Doe");
        mockCustomer.setEmail("jane.doe@example.com");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(mockCustomer));

        // Act
        Customer foundCustomer = customerService.findCustomerById(customerId);

        // Assert
        assertNotNull(foundCustomer);
        assertEquals("Jane Doe", foundCustomer.getName());
        assertEquals("jane.doe@example.com", foundCustomer.getEmail());
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void testFindCustomerById_CustomerNotFound() {
        // Arrange
        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> customerService.findCustomerById(customerId));
        assertEquals("Customer not found", exception.getMessage());
        verify(customerRepository, times(1)).findById(customerId);
    }
}
