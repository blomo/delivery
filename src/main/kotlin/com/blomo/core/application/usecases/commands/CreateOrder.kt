package com.blomo.core.application.usecases.commands

import com.blomo.core.application.usecases.commands.dto.order.CreateOrderDto
import com.blomo.core.domain.aggregates.order.Order
import com.blomo.core.domain.shared.kernel.Location
import org.springframework.stereotype.Component

@Component
class CreateOrder {

    fun execute(createOrder: CreateOrderDto): Order =
        Order(id = createOrder.basketId, location = findLocation())


    private fun findLocation(): Location = Location.createRandom()
}

