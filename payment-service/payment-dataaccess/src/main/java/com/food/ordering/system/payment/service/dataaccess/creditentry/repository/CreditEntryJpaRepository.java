package com.food.ordering.system.payment.service.dataaccess.creditentry.repository;

import com.food.ordering.system.payment.service.dataaccess.creditentry.entity.CreditEntryEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditEntryJpaRepository extends JpaRepository<CreditEntryEntity, UUID> {

  Optional<CreditEntryEntity> findByCustomerId(UUID orderId);

}
