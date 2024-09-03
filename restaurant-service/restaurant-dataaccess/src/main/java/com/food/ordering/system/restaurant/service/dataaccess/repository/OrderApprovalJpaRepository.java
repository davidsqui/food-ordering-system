package com.food.ordering.system.restaurant.service.dataaccess.repository;

import com.food.ordering.system.restaurant.service.dataaccess.entity.OrderApprovalEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderApprovalJpaRepository extends JpaRepository<OrderApprovalEntity, UUID> {

}
