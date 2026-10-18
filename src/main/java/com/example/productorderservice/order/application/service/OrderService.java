package com.example.productorderservice.order.application.service;

import com.example.productorderservice.order.application.port.in.CreateOrderRequest;
import com.example.productorderservice.order.application.port.in.CreateOrderUseCase;
import com.example.productorderservice.order.application.port.in.GetOrderUseCase;
import com.example.productorderservice.order.application.port.out.OrderPort;
import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class OrderService implements CreateOrderUseCase, GetOrderUseCase {

    private final OrderPort orderPort;

    OrderService(final OrderPort orderPort) {
        this.orderPort = orderPort;
    }

    @Override
    @Transactional
    public void createOrder(final CreateOrderRequest request) {
        final Product product = orderPort.getProductById(request.productId());

        final Order order = new Order(product, request.quantity());

        orderPort.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrder(final Long orderId) {
        return orderPort.getOrder(orderId);
    }
}
