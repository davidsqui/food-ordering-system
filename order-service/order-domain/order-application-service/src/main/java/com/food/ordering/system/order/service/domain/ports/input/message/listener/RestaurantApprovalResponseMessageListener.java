package com.food.ordering.system.order.service.domain.ports.input.message.listener;

import com.food.ordering.system.order.service.domain.dto.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {

    void orderApproved(RestaurantApprovalResponse restaurantApprovalResponse);

    void orderRejected(RestaurantApprovalResponse restaurantApprovalResponse);

}
