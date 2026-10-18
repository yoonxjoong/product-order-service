package com.example.productorderservice.order.application.port.in;

import com.example.productorderservice.order.domain.Order;

public interface GetOrderUseCase {

    Order getOrder(Long orderId);
}
