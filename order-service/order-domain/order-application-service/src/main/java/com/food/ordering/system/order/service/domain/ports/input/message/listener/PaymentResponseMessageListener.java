package com.food.ordering.system.order.service.domain.ports.input.message.listener;

import com.food.ordering.system.order.service.domain.dto.PaymentResponse;

public interface PaymentResponseMessageListener {

    void paymentCompleted(PaymentResponse paymentResponse);

    void paymentCancelled(PaymentResponse paymentResponse);
}
