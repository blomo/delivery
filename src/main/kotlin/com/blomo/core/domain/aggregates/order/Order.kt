package com.blomo.core.domain.aggregates.order

import com.blomo.core.domain.aggregates.courier.Courier
import com.blomo.core.domain.shared.kernel.Location
import java.util.*

class Order(
    val id: UUID,
    val location: Location,
) {

    var status: OrderStatus = OrderStatus.CREATED
        private set

    lateinit var courierId: UUID
        private set

    fun assigne(courier: Courier) {
        courierId = courier.id
        status = OrderStatus.ASSIGNED
    }

    fun complete() {
        if (status == OrderStatus.ASSIGNED) {
            status = OrderStatus.COMPLETED
        } else throw IllegalStateException("Order ${this.id} status is not ASSIGNED")
    }

}
