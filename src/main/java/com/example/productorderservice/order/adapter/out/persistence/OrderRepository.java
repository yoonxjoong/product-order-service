package com.example.productorderservice.order.adapter.out.persistence;

import com.example.productorderservice.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OrderRepository extends JpaRepository<Order, Long> {

}
