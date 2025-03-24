package com.blomo.core.services

import com.blomo.core.domain.aggregates.courier.Courier
import com.blomo.core.domain.aggregates.order.Order

interface DispatchServie {
    fun dispatch(order: Order, couriers: List<Courier>): Courier?
}
