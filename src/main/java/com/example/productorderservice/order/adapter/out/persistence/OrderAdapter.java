package com.example.productorderservice.order.adapter.out.persistence;

import com.example.productorderservice.order.application.port.out.OrderPort;
import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.product.application.port.in.GetProductUseCase;
import com.example.productorderservice.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
class OrderAdapter implements OrderPort {

    private final GetProductUseCase getProductUseCase;
    private final OrderRepository orderRepository;

    OrderAdapter(final GetProductUseCase getProductUseCase,
            final OrderRepository orderRepository) {
        this.getProductUseCase = getProductUseCase;
        this.orderRepository = orderRepository;
    }

    @Override
    public Product getProductById(final Long productId) {
        return getProductUseCase.getProduct(productId);
    }

    @Override
    public Order getOrder(final Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));
    }

    @Override
    public void save(final Order order) {
        orderRepository.save(order);
    }
}
