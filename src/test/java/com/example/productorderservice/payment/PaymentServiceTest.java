package com.example.productorderservice.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {

    private PaymentService paymentService;


    @BeforeEach
    void setUp() {
        PaymentGateway consolePaymentGateway = new ConsolePaymentGateway();
        PaymentRepository paymentRepository = new PaymentRepository();
        PaymentPort paymetPort = new PaymentAdapter(consolePaymentGateway, paymentRepository);

        paymentService = new PaymentService(paymetPort);
    }

    @Test
    void 상품결제() {
        PaymentRequest request = PaymentSteps.상품결제요청_생성();

        paymentService.payment(request);
    }

}
