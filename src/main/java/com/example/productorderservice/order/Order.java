package com.example.productorderservice.order;

import com.example.productorderservice.product.Product;
import jakarta.persistence.Entity;
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
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    private int quantity;


    public Order(Product product, int quantity) {
        Assert.notNull(product, "상품정보는 필수입니다.");
        Assert.isTrue(quantity > 0, "수량은 0보다 커야합니다.");
        this.product = product;
        this.quantity = quantity;

    }


    public Long getTotalPrice() {
        return this.product.getDiscountedPrice() * quantity;
    }
}
