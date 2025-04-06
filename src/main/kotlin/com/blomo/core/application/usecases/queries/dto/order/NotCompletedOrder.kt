package com.blomo.core.application.usecases.queries.dto.order

import com.blomo.core.domain.aggregates.order.OrderStatus
import com.blomo.core.domain.shared.kernel.Location
import java.util.*

class NotCompletedOrder(
    val orderId: UUID,
    val location: Location,
    val status: OrderStatus,
) {
    constructor(orderId: UUID, locationX: Int, locationY: Int, status: OrderStatus) : this(
        orderId = orderId,
        location = Location(locationX, locationY),
        status = status
    )
}
