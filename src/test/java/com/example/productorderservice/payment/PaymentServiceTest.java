package com.example.productorderservice.payment;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.order.OrderSteps;
import com.example.productorderservice.product.ProductSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


class PaymentServiceTest extends ApiTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    void 상품결제() {
        ProductSteps.상품등록요청(ProductSteps.상품조회요청_생성());

        OrderSteps.상품주문요청(OrderSteps.상품주문요청_생성());

        PaymentRequest request = PaymentSteps.상품결제요청_생성();

        paymentService.payment(request);
    }

}
