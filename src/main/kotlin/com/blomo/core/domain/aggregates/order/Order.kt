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

    var courierId: UUID? = null
        private set

    companion object {
        fun fromDb(
            id: UUID,
            location: Location,
            status: OrderStatus,
            courierId: UUID?
        ): Order {
            val order = Order(id, location)
            order.status = status
            order.courierId = courierId
            return order
        }
    }

    fun assign(courier: Courier) {
        courierId = courier.id
        status = OrderStatus.ASSIGNED
    }

    fun complete() {
        if (status == OrderStatus.ASSIGNED) {
            status = OrderStatus.COMPLETED
        } else throw IllegalStateException("Order ${this.id} status is not ASSIGNED")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Order) return false
        return this.id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

}
