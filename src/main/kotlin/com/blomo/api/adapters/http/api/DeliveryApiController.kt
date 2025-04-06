package com.blomo.api.adapters.http.api

import com.blomo.api.adapters.http.DefaultApi
import com.blomo.api.adapters.http.api.model.Courier
import com.blomo.api.adapters.http.api.model.Location
import com.blomo.api.adapters.http.api.model.NewOrder
import com.blomo.api.adapters.http.api.model.Order
import com.blomo.core.application.usecases.commands.CreateOrder
import com.blomo.core.application.usecases.commands.dto.order.CreateOrderDto
import com.blomo.core.application.usecases.queries.GetBusyCouriers
import com.blomo.core.application.usecases.queries.GetNotCompletedOrders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class DeliveryApiController(
    private val createOrder: CreateOrder,
    private val getNotCompletedOrders: GetNotCompletedOrders,
    private val getBusyCouriers: GetBusyCouriers,
) : DefaultApi {

    override fun createOrder(newOrder: NewOrder): ResponseEntity<Order> =
        createOrder.execute(
            createOrder = CreateOrderDto(
                basketId = newOrder.id,
                street = newOrder.street
            )
        )
            .run { ResponseEntity.ok(Order(id = id, location = Location(location.x, location.y))) }


    override fun getOrders(): ResponseEntity<List<Order>> =
        getNotCompletedOrders.execute().map {
            Order(id = it.orderId, location = Location(it.location.x, it.location.y))
        }.run { ResponseEntity.ok(this) }


    override fun getCouriers(): ResponseEntity<List<Courier>> =
        getBusyCouriers.execute().map {
            Courier(id = it.courierId, name = it.name, location = Location(it.location.x, it.location.y))
        }.run { ResponseEntity.ok(this) }

}
