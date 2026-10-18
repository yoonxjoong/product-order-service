package com.example.productorderservice.payment;

import org.jspecify.annotations.NonNull;

public class PaymentSteps {

    public static @NonNull PaymentRequest 상품결제요청_생성() {
        final Long orderId = 1L;
        final PaymentMethod paymentMethod = PaymentMethod.CARD;
        PaymentRequest request = new PaymentRequest(orderId, paymentMethod);
        return request;
    }
}
