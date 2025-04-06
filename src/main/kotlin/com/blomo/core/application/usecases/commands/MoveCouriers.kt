package com.blomo.core.application.usecases.commands

import com.blomo.core.domain.aggregates.order.Order
import com.blomo.core.ports.courier.CourierRepository
import com.blomo.core.ports.order.OrderRepository
import org.springframework.stereotype.Component

@Component
class MoveCouriers(
    private val courierRepository: CourierRepository,
    private val orderRepository: OrderRepository,
) {
    fun execute(): List<Order> =
        orderRepository
            .getAllByStatusAssigned()
            .onEach { it.moveCourier() }


    private fun Order.moveCourier() {
        val courier = courierId?.let { courierId ->
            courierRepository.getById(courierId)
        } ?: throw IllegalStateException("courier not found")

        courier.move(location)

        if (courier.location == location)
            complete()
    }
}
