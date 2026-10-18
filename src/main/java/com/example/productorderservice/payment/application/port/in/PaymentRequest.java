package com.example.productorderservice.payment.application.port.in;

import com.example.productorderservice.payment.domain.PaymentMethod;
import org.springframework.util.Assert;

public record PaymentRequest(Long orderId, PaymentMethod paymentMethod) {

    public PaymentRequest {
        Assert.notNull(orderId, "주문번호는 필수 입니다.");
        Assert.notNull(paymentMethod, "결제수단은 필수 입니다.");
    }
}
