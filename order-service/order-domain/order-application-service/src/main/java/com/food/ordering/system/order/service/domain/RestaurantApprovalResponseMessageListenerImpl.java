package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.RestaurantApprovalResponse;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.RestaurantApprovalResponseMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@RequiredArgsConstructor
@Validated
@Service
public class RestaurantApprovalResponseMessageListenerImpl implements
    RestaurantApprovalResponseMessageListener {

  private final OrderApprovalSaga orderApprovalSaga;

  @Override
  public void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse) {
    orderApprovalSaga.process(restaurantApprovalResponse);
    log.info("Order is approved for order id: {}", restaurantApprovalResponse.getOrderId());
  }

  @Override
  public void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse) {
    OrderCancelledEvent domainEvent = orderApprovalSaga.rollback(restaurantApprovalResponse);
    log.info("Publishing OrderCancelledEvent for order id: {} with failure messages: {}",
        restaurantApprovalResponse.getOrderId(),
        String.join(",", restaurantApprovalResponse.getFailureMessages()));
    domainEvent.fire();
  }
}
