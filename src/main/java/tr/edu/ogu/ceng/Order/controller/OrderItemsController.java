package tr.edu.ogu.ceng.Order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ogu.ceng.Order.Service.OrderItemsService;

@RequiredArgsConstructor
@RestController

public class OrderItemsController {

    private final OrderItemsService orderItemsService;

}
