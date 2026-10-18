package com.example.productorderservice.payment.adapter.out.persistence;

import com.example.productorderservice.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PaymentRepository extends JpaRepository<Payment, Long> {

}
