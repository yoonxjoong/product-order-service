package com.example.productorderservice.payment;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.product.ProductSteps;
import com.example.productorderservice.order.OrderSteps;
import com.example.productorderservice.payment.application.port.in.PaymentRequest;
import com.example.productorderservice.payment.application.port.in.PaymentUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class PaymentServiceTest extends ApiTest {

    @Autowired
    private PaymentUseCase paymentUseCase;

    @Test
    void 상품결제() {
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());

        OrderSteps.상품주문요청(OrderSteps.상품주문요청_생성());

        final PaymentRequest request = PaymentSteps.상품결제요청_생성();

        paymentUseCase.payment(request);
    }
}
