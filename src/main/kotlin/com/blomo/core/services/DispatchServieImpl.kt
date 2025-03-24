package com.blomo.core.services

import com.blomo.core.domain.aggregates.courier.Courier
import com.blomo.core.domain.aggregates.courier.CourierStatus
import com.blomo.core.domain.aggregates.order.Order

class DispatchServieImpl : DispatchServie {
    override fun dispatch(order: Order, couriers: List<Courier>): Courier? =
        couriers
            .filter { it.status == CourierStatus.FREE }
            .takeIf { it.isNotEmpty() }
            ?.associateBy { it.calculateTimeToLocation(order.location) }
            ?.toSortedMap()
            ?.firstNotNullOf { it.value }

}
