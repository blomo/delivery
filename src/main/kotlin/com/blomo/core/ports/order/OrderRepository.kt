package com.blomo.core.ports.order

import com.blomo.core.domain.aggregates.order.Order
import java.util.*

interface OrderRepository {
    fun add(order: Order)
    fun update(order: Order): Order
    fun getById(id: UUID): Order?
    fun getAnyByStatusCreated(): Order?
    fun getAllByStatusAssigned(): List<Order>
}
