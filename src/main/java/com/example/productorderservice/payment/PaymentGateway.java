package com.example.productorderservice.payment;

interface PaymentGateway {

    void execute(Payment payment);

    void execute(Long price, String method);
}
