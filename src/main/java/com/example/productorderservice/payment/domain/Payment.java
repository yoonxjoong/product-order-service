package com.example.productorderservice.payment.domain;

import com.example.productorderservice.order.domain.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    public Payment(Order order, PaymentMethod paymentMethod) {
        Assert.notNull(order, "주문정보는 필수입니다.");
        Assert.notNull(paymentMethod, "결제수단은 필수입니다.");

        this.order = order;
        this.paymentMethod = paymentMethod;
    }

    public Long getPrice() {
        return order.getTotalPrice();
    }
}
