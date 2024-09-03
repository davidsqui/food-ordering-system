package com.food.ordering.system.order.service.messaging.mapper;

import com.food.ordering.system.domain.valueobject.PaymentStatus;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.kafka.order.avro.model.Product;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus;
import com.food.ordering.system.order.service.domain.dto.PaymentResponse;
import com.food.ordering.system.order.service.domain.dto.RestaurantApprovalResponse;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.event.OrderCancelledEvent;
import com.food.ordering.system.order.service.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.event.OrderPaidEvent;

@Component
public class OrderMessagingDataMapper {

        public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(OrderCreatedEvent orderCreatedEvent) {
                Order order = orderCreatedEvent.getOrder();
                return PaymentRequestAvroModel.newBuilder()
                                .setId(UUID.randomUUID().toString())
                                .setSagaId("")
                                .setCustomerId(order.getCustomerId().getValue().toString())
                                .setOrderId(order.getId().getValue().toString())
                                .setPrice(order.getPrice().getAmount())
                                .setCreatedAt(orderCreatedEvent.getCreatedAt().toInstant())
                                .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
                                .build();
        }

        public PaymentRequestAvroModel orderCancelEventToPaymentRequestAvroModel(OrderCancelledEvent orderCancelEvent) {
                Order order = orderCancelEvent.getOrder();
                return PaymentRequestAvroModel.newBuilder()
                                .setId(UUID.randomUUID().toString())
                                .setSagaId("")
                                .setCustomerId(order.getCustomerId().getValue().toString())
                                .setOrderId(order.getId().getValue().toString())
                                .setPrice(order.getPrice().getAmount())
                                .setCreatedAt(orderCancelEvent.getCreatedAt().toInstant())
                                .setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
                                .build();
        }

        public RestaurantApprovalRequestAvroModel orderPaidEventToRestaurantApprovalRequestAvroModel(
                        OrderPaidEvent orderPaidEvent) {
                Order order = orderPaidEvent.getOrder();
                return RestaurantApprovalRequestAvroModel.newBuilder()
                                .setId(UUID.randomUUID().toString())
                                .setSagaId("")
                                .setOrderId(order.getId().getValue().toString())
                                .setRestaurantId(order.getRestaurantId().getValue().toString())
                                .setProducts(order.getItems().stream().map(item -> Product.newBuilder()
                                                .setId(item.getProduct().getId().getValue().toString())
                                                .setQuantity(item.getQuantity())
                                                .build()).toList())
                                .setPrice(order.getPrice().getAmount())
                                .setCreatedAt(orderPaidEvent.getCreatedAt().toInstant())
                                .setRestaurantOrderStatus(RestaurantOrderStatus.PAID)
                                .build();
        }

        public PaymentResponse paymentResponseAvroModelToPaymentResponse(
                        PaymentResponseAvroModel paymentResponseAvroModel) {
                return PaymentResponse.builder()
                                .id(paymentResponseAvroModel.getId())
                                .sagaId(paymentResponseAvroModel.getSagaId())
                                .paymentId(paymentResponseAvroModel.getPaymentId())
                                .customerId(paymentResponseAvroModel.getCustomerId())
                                .orderId(paymentResponseAvroModel.getOrderId())
                                .price(paymentResponseAvroModel.getPrice())
                                .createdAt(paymentResponseAvroModel.getCreatedAt())
                                .paymentStatus(PaymentStatus
                                                .valueOf(paymentResponseAvroModel.getPaymentStatus().name()))
                                .failureMessages(paymentResponseAvroModel.getFailureMessages())
                                .build();
        }

        public RestaurantApprovalResponse approvalResponseAvroModelToApprovalResponse(
                        RestaurantApprovalResponseAvroModel restaurantApprovalResponseAvroModel) {
                return RestaurantApprovalResponse.builder()
                                .id(restaurantApprovalResponseAvroModel.getId())
                                .sagaId(restaurantApprovalResponseAvroModel.getSagaId())
                                .restaurantId(restaurantApprovalResponseAvroModel.getRestaurantId())
                                .orderId(restaurantApprovalResponseAvroModel.getOrderId())
                                .createdAt(restaurantApprovalResponseAvroModel.getCreatedAt())
                                .orderApprovalStatus(OrderApprovalStatus
                                                .valueOf(restaurantApprovalResponseAvroModel.getOrderApprovalStatus()
                                                                .name()))
                                .failureMessages(restaurantApprovalResponseAvroModel.getFailureMessages())
                                .build();
        }

}
