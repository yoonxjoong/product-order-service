package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class PaymentService {

    private final PaymentPort paymentPort;

    PaymentService(final PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    @Transactional
    public void payment(PaymentRequest request) {
        Order order = paymentPort.getOrderById(request.orderId());

        final Payment payment = new Payment(order, request.paymentMethod());

        paymentPort.pay(payment.getPrice(), payment.getPaymentMethod().name());
        paymentPort.save(payment);
    }
}
