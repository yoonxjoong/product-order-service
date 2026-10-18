package com.example.productorderservice.payment;

import org.springframework.util.Assert;

record PaymentRequest(Long orderId, PaymentMethod paymentMethod) {

    PaymentRequest {
        Assert.notNull(orderId, "주문번호는 필수 입니다.");
    }
}
