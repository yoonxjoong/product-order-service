package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;

class PaymentService {

    private final PaymentPort paymentPort;

    PaymentService(final PaymentPort paymentPort) {
        this.paymentPort = paymentPort;
    }

    public void payment(PaymentRequest request) {
        Order order = paymentPort.getOrderById(request.orderId());

        final Payment payment = new Payment(order, request.paymentMethod());

        paymentPort.pay(payment.getPrice(), payment.getPaymentMethod().getMethod());
        paymentPort.save(payment);
    }
}
