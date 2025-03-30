package com.blomo.core.application.usecases.queries

import com.blomo.core.application.usecases.queries.dto.order.NotCompletedOrder
import com.blomo.core.ports.order.OrderRepository
import org.springframework.stereotype.Component

@Component
class GetNotCompletedOrders(
    private val orderRepository: OrderRepository,
) {
    fun execute(): List<NotCompletedOrder>  =
        orderRepository.getAllNotCompleted()
}
