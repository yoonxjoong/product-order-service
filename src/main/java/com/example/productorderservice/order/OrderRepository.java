package com.example.productorderservice.order;

import java.util.HashMap;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OrderRepository extends JpaRepository<Order, Long> {


}