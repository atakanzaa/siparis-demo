package tr.edu.ogu.ceng.Order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.Order.entity.Payment;
import tr.edu.ogu.ceng.Order.repository.PaymentRepository;


@Service
@RequiredArgsConstructor

public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}