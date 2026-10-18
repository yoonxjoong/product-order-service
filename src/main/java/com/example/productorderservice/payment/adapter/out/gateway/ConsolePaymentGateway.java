package com.example.productorderservice.payment.adapter.out.gateway;

import com.example.productorderservice.payment.application.port.out.PaymentGateway;
import org.springframework.stereotype.Component;

@Component
class ConsolePaymentGateway implements PaymentGateway {

    @Override
    public void execute(final Long price, final String method) {
        System.out.println("결제 완료: " + price + "원 / " + method);
    }
}
