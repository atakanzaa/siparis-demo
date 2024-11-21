package tr.edu.ogu.ceng.Order.ServiceTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tr.edu.ogu.ceng.Order.Service.ProductService;
import tr.edu.ogu.ceng.Order.entity.Product;
import tr.edu.ogu.ceng.Order.repository.ProductRepository;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    void testSaveProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Laptop");
        product.setDescription("A powerful gaming laptop");
        product.setPrice(1500.0);
        product.setStock(10);

        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product savedProduct = productService.saveProduct(product);

        // Assert
        assertNotNull(savedProduct);
        assertEquals("Laptop", savedProduct.getName());
        assertEquals("A powerful gaming laptop", savedProduct.getDescription());
        assertEquals(1500.0, savedProduct.getPrice());
        assertEquals(10, savedProduct.getStock());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testFindProductById() {
        // Arrange
        Long productId = 1L;
        Product mockProduct = new Product();
        mockProduct.setProductId(productId);
        mockProduct.setName("Phone");
        mockProduct.setPrice(800.0);

        when(productRepository.findById(productId)).thenReturn(Optional.of(mockProduct));

        // Act
        Product foundProduct = productService.findProductById(productId);

        // Assert
        assertNotNull(foundProduct);
        assertEquals("Phone", foundProduct.getName());
        assertEquals(800.0, foundProduct.getPrice());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testFindProductById_ProductNotFound() {
        // Arrange
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> productService.findProductById(productId));
        assertEquals("Product not found", exception.getMessage());
        verify(productRepository, times(1)).findById(productId);
    }
}
