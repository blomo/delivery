package com.blomo.api.adapters.kafka.basket

import BasketConfirmed.BasketConfirmedEvent
import com.blomo.core.application.usecases.commands.CreateOrder
import com.blomo.core.application.usecases.commands.dto.order.CreateOrderDto
import java.util.*
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component


@Component
class BasketConsumer(
    private val createOrder: CreateOrder,
) {
    @KafkaListener(topics = ["basket.confirmed"])
    fun listen(record: ConsumerRecord<*, BasketConfirmedEvent.BasketConfirmedIntegrationEvent>) {
        record
            .value()
            .let { basketEvent ->
                createOrder.execute(
                    CreateOrderDto(
                        basketId = basketEvent.basketId.run(UUID::fromString),
                        street = basketEvent.address.street
                    )
                )
            }
    }
}
