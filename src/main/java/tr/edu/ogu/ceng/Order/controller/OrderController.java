package tr.edu.ogu.ceng.Order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ogu.ceng.Order.service.OrderService;


@RequiredArgsConstructor
@RestController

public class OrderController {

    private final OrderService orderService;

}
