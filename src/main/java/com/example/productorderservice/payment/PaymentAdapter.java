package com.example.productorderservice.payment;

import com.example.productorderservice.order.Order;
import com.example.productorderservice.order.OrderRepository;
import com.example.productorderservice.product.DiscountPolicy;
import com.example.productorderservice.product.Product;
import org.springframework.stereotype.Component;

@Component
class PaymentAdapter implements PaymentPort {

    private final PaymentGateway paymentGateway;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;


    PaymentAdapter(PaymentGateway paymentGateway,
            PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다"));
    }

    @Override
    public void pay(Payment payment) {
        paymentGateway.execute(payment);
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void pay(Long price, String method) {
        paymentGateway.execute(price, method);
    }
}
