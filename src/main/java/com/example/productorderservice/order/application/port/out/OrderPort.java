package com.example.productorderservice.order.application.port.out;

import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.product.domain.Product;

public interface OrderPort {

    Product getProductById(Long productId);

    Order getOrder(Long orderId);

    void save(Order order);
}
