package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import lombok.Getter;

class Payment {

    @Getter
    private Long id;

    private final Order order;
    private final PaymentMethod paymentMethod;

    public Payment(Order order, PaymentMethod paymentMethod) {
        this.order = order;
        this.paymentMethod = paymentMethod;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        order.getTotalPrice();
    }
}
