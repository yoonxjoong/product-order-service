package com.example.productorderservice.payment;

import com.example.productorderservice.payment.application.port.in.PaymentRequest;
import com.example.productorderservice.payment.domain.PaymentMethod;

public class PaymentSteps {

    public static PaymentRequest 상품결제요청_생성() {
        final Long orderId = 1L;
        final PaymentMethod paymentMethod = PaymentMethod.CARD;
        return new PaymentRequest(orderId, paymentMethod);
    }
}
