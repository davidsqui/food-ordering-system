package com.food.ordering.system.restaurant.service.messaging.mapper;

import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.OrderApprovalStatus;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.food.ordering.system.restaurant.service.domain.entity.Product;
import com.food.ordering.system.restaurant.service.domain.event.OrderApprovalEvent;
import com.food.ordering.system.restaurant.service.domain.event.OrderRejectedEvent;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMessagingDataMapper {

  public RestaurantApprovalResponseAvroModel orderApprovalEventToRestaurantApprovalResponseAvroModel(
      OrderApprovalEvent orderApprovalEvent) {
    return RestaurantApprovalResponseAvroModel.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setSagaId("")
        .setOrderId(orderApprovalEvent.getOrderApproval().getOrderId().getValue().toString())
        .setRestaurantId(orderApprovalEvent.getRestaurantId().getValue().toString())
        .setCreatedAt(orderApprovalEvent.getCreatedAt().toInstant())
        .setOrderApprovalStatus(OrderApprovalStatus.valueOf(
            orderApprovalEvent.getOrderApproval().getApprovalStatus().name()))
        .setFailureMessages(orderApprovalEvent.getFailureMessages())
        .build();
  }

  public RestaurantApprovalResponseAvroModel orderRejectedEventToRestaurantApprovalResponseAvroModel(
      OrderRejectedEvent orderRejectedEvent) {
    return RestaurantApprovalResponseAvroModel.newBuilder()
        .setId(UUID.randomUUID().toString())
        .setSagaId("")
        .setOrderId(orderRejectedEvent.getOrderApproval().getOrderId().getValue().toString())
        .setRestaurantId(orderRejectedEvent.getRestaurantId().getValue().toString())
        .setCreatedAt(orderRejectedEvent.getCreatedAt().toInstant())
        .setOrderApprovalStatus(OrderApprovalStatus.valueOf(
            orderRejectedEvent.getOrderApproval().getApprovalStatus().name()))
        .setFailureMessages(orderRejectedEvent.getFailureMessages())
        .build();
  }

  public RestaurantApprovalRequest restaurantApprovalRequestAvroModelToRestaurantApprovalRequest(
      RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel) {
    return RestaurantApprovalRequest.builder()
        .id(restaurantApprovalRequestAvroModel.getId())
        .sagaId(restaurantApprovalRequestAvroModel.getSagaId())
        .restaurantId(restaurantApprovalRequestAvroModel.getRestaurantId())
        .orderId(restaurantApprovalRequestAvroModel.getOrderId())
        .restaurantOrderStatus(RestaurantOrderStatus.valueOf(
            restaurantApprovalRequestAvroModel.getRestaurantOrderStatus().name()))
        .products(restaurantApprovalRequestAvroModel.getProducts().stream()
            .map(product -> Product.builder()
                .productId(new ProductId(UUID.fromString(product.getId())))
                .quantity(product.getQuantity())
                .build())
            .toList())
        .price(restaurantApprovalRequestAvroModel.getPrice())
        .createdAt(restaurantApprovalRequestAvroModel.getCreatedAt())
        .build();
  }

}
