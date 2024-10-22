package tr.edu.ogu.ceng.Order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.Order.entity.Product;
import tr.edu.ogu.ceng.Order.repository.ProductRepository;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
