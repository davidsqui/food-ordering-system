package com.food.ordering.system.payment.service.dataaccess.payment.repository;

import com.food.ordering.system.payment.service.dataaccess.payment.entity.PaymentEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, UUID> {

  Optional<PaymentEntity> findByOrderId(UUID orderId);

}
