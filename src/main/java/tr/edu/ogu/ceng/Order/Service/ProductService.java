package tr.edu.ogu.ceng.Order.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tr.edu.ogu.ceng.Order.dto.ProductDto;
import tr.edu.ogu.ceng.Order.entity.Payment;
import tr.edu.ogu.ceng.Order.entity.Product;
import tr.edu.ogu.ceng.Order.repository.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;

    private final RestClient restClient;

    public Product saveProduct(Product product) {
        restClient.get().uri("http://192.168.137.169:8003/api/v1/product/{id}","id")
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Payment.class);

        return productRepository.save(product);
    }
    public ProductDto findProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return convertToDto(optionalProduct.get());
    }
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        dto.setDeletedAt(product.getDeletedAt());
        dto.setCreatedBy(product.getCreatedBy());
        dto.setUpdatedBy(product.getUpdatedBy());
        dto.setDeletedBy(product.getDeletedBy());
        dto.setVersion(product.getVersion());
        return dto;
    }
}
