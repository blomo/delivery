package com.blomo.infrastructure.adapters.kafka.mapper

import OrderStatusChanged.OrderStatusChangedIntegrationEventOuterClass
import com.blomo.core.application.events.order.OrderEvent
import com.blomo.core.application.events.order.OrderStatusChanged
import com.blomo.core.domain.aggregates.order.OrderStatus

fun OrderEvent.asKafka(): OrderStatusChangedIntegrationEventOuterClass.OrderStatusChangedIntegrationEvent =
    when (this) {
        is OrderStatusChanged ->
            OrderStatusChangedIntegrationEventOuterClass.OrderStatusChangedIntegrationEvent
                .newBuilder()
                .setOrderId(id.toString())
                .setOrderStatus(newStatus.asKafka())
                .build()
    }

private fun OrderStatus.asKafka(): OrderStatusChangedIntegrationEventOuterClass.OrderStatus =
    when (this) {
        OrderStatus.CREATED -> OrderStatusChangedIntegrationEventOuterClass.OrderStatus.Created
        OrderStatus.ASSIGNED -> OrderStatusChangedIntegrationEventOuterClass.OrderStatus.Assigned
        OrderStatus.COMPLETED -> OrderStatusChangedIntegrationEventOuterClass.OrderStatus.Completed
    }
