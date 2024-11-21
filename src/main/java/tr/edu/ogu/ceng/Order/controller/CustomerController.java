package tr.edu.ogu.ceng.Order.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ogu.ceng.Order.Service.CustomerService;

@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;


}
