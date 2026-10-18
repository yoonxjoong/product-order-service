package com.example.productorderservice.payment.application.port.out;

public interface PaymentGateway {

    void execute(Long price, String method);
}
