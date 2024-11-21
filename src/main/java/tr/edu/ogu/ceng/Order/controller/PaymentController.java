package tr.edu.ogu.ceng.Order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ogu.ceng.Order.Service.PaymentService;


@RequiredArgsConstructor
@RestController

public class PaymentController {

    private final PaymentService paymentService;

}
