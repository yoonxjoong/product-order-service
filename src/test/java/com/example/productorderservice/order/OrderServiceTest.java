package com.example.productorderservice.order;

import com.example.productorderservice.ApiTest;
import com.example.productorderservice.order.application.port.in.CreateOrderRequest;
import com.example.productorderservice.order.application.port.in.CreateOrderUseCase;
import com.example.productorderservice.product.ProductSteps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class OrderServiceTest extends ApiTest {

    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @Test
    void 주문생성() {
        ProductSteps.상품등록요청(ProductSteps.상품추가_요청());

        final Long productId = 1L;
        final int quantity = 2;
        final CreateOrderRequest request = new CreateOrderRequest(productId, quantity);

        createOrderUseCase.createOrder(request);
    }
}
