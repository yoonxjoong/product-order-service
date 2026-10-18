package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;

interface PaymentPort {

    Order getOrderById(Long orderId);

    void pay(Payment payment);

    void save(Payment payment);

    void pay(Long price, String method);
}
