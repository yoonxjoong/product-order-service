package com.example.productorderservice.payment;

public class ConsolePaymentGateway implements PaymentGateway {

    @Override
    public void execute(Payment payment) {
        System.out.println("결제 완료");
    }
}
