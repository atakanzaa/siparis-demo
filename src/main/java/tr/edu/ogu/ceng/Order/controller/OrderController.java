package tr.edu.ogu.ceng.Order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ogu.ceng.Order.Service.OrderService;


@RequiredArgsConstructor
@RestController

public class OrderController {


    private final OrderService orderService;

}
