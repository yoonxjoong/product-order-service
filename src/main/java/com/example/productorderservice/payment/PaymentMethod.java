package com.example.productorderservice.payment;

import org.springframework.util.Assert;

class PaymentMethod {

    private final String method;

    public PaymentMethod(String method) {
        Assert.notNull(method, "결제 방법은 필수입니다.");

        this.method = method;
    }
}
