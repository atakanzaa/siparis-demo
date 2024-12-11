package tr.edu.ogu.ceng.Order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.ogu.ceng.Order.Service.PaymentService;
import tr.edu.ogu.ceng.Order.dto.PaymentDto;
import tr.edu.ogu.ceng.Order.entity.Payment;

@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id) {
        PaymentDto payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        PaymentDto createdPayment = paymentService.createPayment(paymentDto);
        return ResponseEntity.status(201).body(createdPayment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable Long id, @RequestBody PaymentDto paymentDto) {
        PaymentDto updatedPayment = paymentService.updatePayment(id, paymentDto);
        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
