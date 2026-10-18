package com.example.productorderservice.payment.application.port.out;

import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.payment.domain.Payment;

public interface PaymentPort {

    Order getOrderById(Long orderId);

    void save(Payment payment);
}
