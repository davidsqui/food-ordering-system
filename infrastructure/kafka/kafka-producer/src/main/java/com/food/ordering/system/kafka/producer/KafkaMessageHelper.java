package com.food.ordering.system.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class KafkaMessageHelper {

    public <T> ListenableFutureCallback<SendResult<String, T>> getKafkaCallback(
            String responseTopicName, T requestAvroModel, String orderId, String requestAvroModelName) {

        return new ListenableFutureCallback<SendResult<String, T>>() {

            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending {} message: {} to topic: {}",
                        requestAvroModelName,
                        requestAvroModel.toString(),
                        responseTopicName, ex);
            }

            @Override
            public void onSuccess(SendResult<String, T> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info(
                        "Received successful response from Kafka for order id: {} Topic: {} Partition: {} Offset: {} TimeStamp: {}",
                        orderId,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());
            }

        };
    }

}
