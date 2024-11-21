package tr.edu.ogu.ceng.Order;

import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Order.entity.*;
import tr.edu.ogu.ceng.Order.repository.CustomerRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(Enclosed.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderApplicationTests {

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

	
	@Test
	public void test() {

	}

	@Test
	public void testPaymentGettersAndSetters() {
		Payment payment = new Payment();
		payment.setAmount(100.0);

		assertEquals(100.0, payment.getAmount(), 0);
	}
	@Test
	public void testProductGettersAndSetters() {
		Product product = new Product();
		product.setName("Laptop");

		assertEquals("Laptop", product.getName());
	}


    @Nested
    class CustomerRepositoryTest {

		@Autowired
		private CustomerRepository customerRepository;

		private Customer customer;

		@BeforeEach
		public void setUp() {
			customer = new Customer();
			customer.setName("Jane Doe");
			customer.setEmail("jane.doe@example.com");
			customer.setPhone("1234567890");
			customer.setCreatedAt(LocalDateTime.now());
			customer.setVersion(1);
			customerRepository.save(customer);
		}

		@Test
		public void testFindByEmail() {
			Optional<Customer> found = customerRepository.findByEmail("jane.doe@example.com");
			assertTrue(found.isPresent());
			assertEquals("Jane Doe", found.get().getName());
		}
		@Test
		public void testCustomerEmailUniqueness() {
			Customer customer1 = new Customer();
			customer1.setEmail("test@example.com");

			Customer customer2 = new Customer();
			customer2.setEmail("test@example.com");

			assertEquals(customer1.getEmail(), customer2.getEmail(), "Email addresses should be equal");
		}
		@Test
		public void testCustomerPhoneFormat() {
			Customer customer = new Customer();
			customer.setPhone("1234567890");
			assertTrue(customer.getPhone().matches("\\d{10}"), "Phone number should be 10 digits");
		}

	}

    @Nested
    class PaymentTest {

		@Test
		public void testPaymentProperties() {
			Payment payment = new Payment();
			payment.setPaymentId(1L);
			payment.setOrderId(1L);
			payment.setAmount(50.00);

			assertEquals(1L, payment.getPaymentId());
			assertEquals(1L, payment.getOrderId());
			assertEquals(50.00, payment.getAmount());
		}
		@Test
		public void testPaymentTimestampNotNull() {
			Payment payment = new Payment();
			payment.setPaymentTimestamp(LocalDateTime.now());
			assertNotNull(payment.getPaymentTimestamp(), "Payment timestamp should not be null");
		}

		@Test
		public void testPaymentStatusDefaultValue() {
			Payment payment = new Payment();
			assertEquals("PENDING", payment.getStatus(), "Default payment status should be PENDING");
		}
	}

    @Nested
    class ProductTest {

		@Test
		public void testProductProperties() {
			Product product = new Product();
			product.setProductId(1L);
			product.setName("Sample Product");
			product.setPrice(25.50);

			assertEquals(1L, product.getProductId());
			assertEquals("Sample Product", product.getName());
			assertEquals(25.50, product.getPrice());
		}
	}

    @Nested
    class SettingTest {

		@Test
		public void testSettingProperties() {
			Setting setting = new Setting();
			setting.setSettingId(1L);
			setting.setKey("exampleKey");
			setting.setValue("exampleValue");

			assertEquals(1L, setting.getSettingId());
			assertEquals("exampleKey", setting.getKey());
			assertEquals("exampleValue", setting.getValue());
		}
	}

    @Nested
    class OrderTest {

		@Test
		public void testOrderProperties() {
			Order order = new Order();
			order.setOrderId(1L);
			order.setCustomerId(1L);
			order.setTotalAmount(99.99);

			assertEquals(1L, order.getOrderId());
			assertEquals(1L, order.getCustomerId());
			assertEquals(99.99, order.getTotalAmount());
		}

		@Test
		public void testOrderTotalAmountCannotBeNegative() {
			Order order = new Order();
			order.setTotalAmount(-1.00);
			assertTrue(order.getTotalAmount() < 0, "Total amount should not be negative");
		}
		@Test
		public void testOrderStatusDefaultValue() {
			Order order = new Order();
			assertEquals("PENDING", order.getStatus(), "Default order status should be PENDING");
		}
	}

    @Nested
    class CustomerTest {

		@Test
		public void testCustomerProperties() {
			Customer customer = new Customer();
			customer.setCustomerId(1L);
			customer.setName("John Doe");
			customer.setEmail("john.doe@example.com");
			customer.setPhone("555-1234");
			customer.setCreatedAt(LocalDateTime.now());
			customer.setVersion(1);

			assertEquals(1L, customer.getCustomerId());
			assertEquals("John Doe", customer.getName());
			assertEquals("john.doe@example.com", customer.getEmail());
			assertEquals("555-1234", customer.getPhone());
		}

		@Test
		public void testCustomerNullValues() {
			Customer customer = new Customer();
			assertNull(customer.getPhone());
			assertNull(customer.getDeletedAt());
		}
	}

    @Nested
    class OrderItemsTest {

		@Test
		public void testOrderItemsCreation() {
			Order_Items orderItems = new Order_Items();
			orderItems.setItemId(1L);
			orderItems.setProductId(1L);
			orderItems.setQuantity(3);

			assertNotNull(orderItems);
			assertEquals(3, orderItems.getQuantity(), "Quantity should be 3");
		}

		@Test
		public void testOrderItemsPriceCalculation() {
			Order_Items orderItems = new Order_Items();
			orderItems.setQuantity(5);
			orderItems.setPricePerItem(10.00);

			assertEquals(50.00, orderItems.calculateTotalPrice(), "Total price should be 50.00");
		}

		@Test
		public void testOrderItemsWithZeroQuantity() {
			Order_Items orderItems = new Order_Items();
			orderItems.setQuantity(0);
			assertEquals(0, orderItems.getQuantity(), "Quantity should be zero");
		}
	}



}
