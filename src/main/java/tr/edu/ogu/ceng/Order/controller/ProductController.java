package tr.edu.ogu.ceng.Order.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ogu.ceng.Order.Service.ProductService;

@RequiredArgsConstructor
@RestController

public class ProductController {

    private final ProductService productService;

}
