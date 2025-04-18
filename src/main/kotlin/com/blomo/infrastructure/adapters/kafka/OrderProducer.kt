package com.blomo.infrastructure.adapters.kafka

import com.blomo.core.application.events.order.OrderEvent
import com.blomo.infrastructure.adapters.kafka.mapper.asKafka
import com.google.protobuf.Message
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OrderProducer(
    private val kafkaTemplate: KafkaTemplate<Long, Message>,
) {
    fun send(event: OrderEvent) {
        kafkaTemplate.send(kafkaTemplate.defaultTopic, event.asKafka())
    }
}
