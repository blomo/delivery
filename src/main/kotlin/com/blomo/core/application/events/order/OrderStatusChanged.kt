package com.blomo.core.application.events.order

import com.blomo.core.domain.aggregates.order.OrderStatus
import java.util.UUID

data class OrderStatusChanged(
    val id: UUID,
    val newStatus: OrderStatus,
) : OrderEvent
