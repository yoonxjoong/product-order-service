package com.example.productorderservice.payment.adapter.out.persistence;

import com.example.productorderservice.order.application.port.in.GetOrderUseCase;
import com.example.productorderservice.order.domain.Order;
import com.example.productorderservice.payment.application.port.out.PaymentPort;
import com.example.productorderservice.payment.domain.Payment;
import org.springframework.stereotype.Component;

@Component
class PaymentAdapter implements PaymentPort {

    private final GetOrderUseCase getOrderUseCase;
    private final PaymentRepository paymentRepository;

    PaymentAdapter(final GetOrderUseCase getOrderUseCase,
            final PaymentRepository paymentRepository) {
        this.getOrderUseCase = getOrderUseCase;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Order getOrderById(final Long orderId) {
        return getOrderUseCase.getOrder(orderId);
    }

    @Override
    public void save(final Payment payment) {
        paymentRepository.save(payment);
    }
}
