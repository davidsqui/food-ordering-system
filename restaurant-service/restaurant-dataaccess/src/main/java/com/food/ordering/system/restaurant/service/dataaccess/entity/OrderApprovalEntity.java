package com.food.ordering.system.restaurant.service.dataaccess.entity;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_approval", schema = "restaurant")
@Entity
public class OrderApprovalEntity {

  @Id
  private UUID id;
  private UUID restaurantId;
  private UUID orderId;
  @Enumerated(EnumType.STRING)
  private OrderApprovalStatus status;

}
