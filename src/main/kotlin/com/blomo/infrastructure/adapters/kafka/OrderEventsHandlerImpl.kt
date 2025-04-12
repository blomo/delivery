package com.blomo.infrastructure.adapters.kafka

import com.blomo.core.application.events.order.OrderStatusChanged
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class OrderEventsHandlerImpl(
    private val producer: OrderProducer,
) {

    @EventListener
    fun handle(orderEvent: OrderStatusChanged) {
        producer.send(orderEvent)
    }
}
