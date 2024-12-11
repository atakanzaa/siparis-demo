package tr.edu.ogu.ceng.Order.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tr.edu.ogu.ceng.Order.Service.ProductService;
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@RestController

public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public Object getProduct(@PathVariable Long id) {
        return productService.findProductById(id);

    }


}
