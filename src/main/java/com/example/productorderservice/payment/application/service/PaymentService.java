package com.example.productorderservice.payment.application.service;

import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.payment.application.port.in.PaymentRequest;
import com.example.productorderservice.payment.application.port.in.PaymentUseCase;
import com.example.productorderservice.payment.application.port.out.PaymentGateway;
import com.example.productorderservice.payment.application.port.out.PaymentPort;
import com.example.productorderservice.payment.domain.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class PaymentService implements PaymentUseCase {

    private final PaymentPort paymentPort;
    private final PaymentGateway paymentGateway;

    PaymentService(final PaymentPort paymentPort, final PaymentGateway paymentGateway) {
        this.paymentPort = paymentPort;
        this.paymentGateway = paymentGateway;
    }

    @Override
    @Transactional
    public void payment(final PaymentRequest request) {
        final Order order = paymentPort.getOrderById(request.orderId());

        final Payment payment = new Payment(order, request.paymentMethod());

        paymentGateway.execute(payment.getPrice(), payment.getPaymentMethod().name());

        paymentPort.save(payment);
    }
}
