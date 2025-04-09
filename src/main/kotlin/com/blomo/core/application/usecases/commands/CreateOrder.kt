package com.blomo.core.application.usecases.commands

import com.blomo.core.application.usecases.commands.dto.order.CreateOrderDto
import com.blomo.core.domain.aggregates.order.Order
import com.blomo.core.domain.shared.kernel.Location
import com.blomo.core.ports.order.GeoClient
import com.blomo.core.ports.order.OrderRepository
import org.springframework.stereotype.Component

@Component
class CreateOrder(
    private val orderRepository: OrderRepository,
    private val geoClient: GeoClient,
    ) {

    fun execute(createOrder: CreateOrderDto): Order =
        Order(id = createOrder.basketId, location = findLocation(createOrder.street)).also {
            orderRepository.add(it)
        }


    private fun findLocation(street: String): Location =
        geoClient.findLocation(street)
}

