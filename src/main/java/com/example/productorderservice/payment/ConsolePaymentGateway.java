package com.example.productorderservice.payment;

import org.springframework.stereotype.Component;

@Component
public class ConsolePaymentGateway implements PaymentGateway {

    @Override
    public void execute(Payment payment) {
        System.out.println("결제 완료");
    }

    @Override
    public void execute(Long price, String method) {
        System.out.println("결제 완료");
    }
}
