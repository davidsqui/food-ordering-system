package com.food.ordering.system.restaurant.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.restaurant.service.domain.valueobject.OrderApprovalId;

public class OrderApproval extends BaseEntity<OrderApprovalId> {

  private final RestaurantId restaurantId;
  private final OrderId orderId;
  private final OrderApprovalStatus approvalStatus;

  private OrderApproval(Builder builder) {
    setId(builder.orderApprovalId);
    this.restaurantId = builder.restaurantId;
    this.orderId = builder.orderId;
    this.approvalStatus = builder.approvalStatus;
  }

  public static Builder builder() {
    return new Builder();
  }


  public RestaurantId getRestaurantId() {
    return restaurantId;
  }

  public OrderId getOrderId() {
    return orderId;
  }

  public OrderApprovalStatus getApprovalStatus() {
    return approvalStatus;
  }

  public static class Builder {

    private OrderApprovalId orderApprovalId;
    private RestaurantId restaurantId;
    private OrderId orderId;
    private OrderApprovalStatus approvalStatus;

    public Builder orderApprovalId(OrderApprovalId orderApprovalId) {
      this.orderApprovalId = orderApprovalId;
      return this;
    }

    public Builder restaurantId(RestaurantId restaurantId) {
      this.restaurantId = restaurantId;
      return this;
    }

    public Builder orderId(OrderId orderId) {
      this.orderId = orderId;
      return this;
    }

    public Builder approvalStatus(OrderApprovalStatus approvalStatus) {
      this.approvalStatus = approvalStatus;
      return this;
    }

    public OrderApproval build() {
      return new OrderApproval(this);
    }
  }

}
