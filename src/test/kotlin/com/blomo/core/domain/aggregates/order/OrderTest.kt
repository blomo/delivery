package com.blomo.core.domain.aggregates.order

import com.blomo.core.domain.aggregates.courier.Courier
import com.blomo.core.domain.aggregates.courier.Speed
import com.blomo.core.domain.aggregates.courier.Transport
import com.blomo.core.domain.shared.kernel.Location
import java.util.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class OrderTest {

    @Test
    fun shouldCreateOrderWithStatusCREATED() {
        val order = Order(id = UUID.randomUUID(), location = Location(1, 1))

        Assertions.assertThat(order.status).isEqualTo(OrderStatus.CREATED)
    }

    @Test
    fun shouldAssignCourierToOrderAndChangeStatusToASSIGNED() {
        val order = Order(id = UUID.randomUUID(), location = Location(1, 1))
        val courier = Courier(
            name = "Василий",
            transport = Transport(name = "Car", speed = Speed(2)),
            _location = Location(1, 1)
        )

        order.assign(courier)

        Assertions.assertThat(order.status).isEqualTo(OrderStatus.ASSIGNED)
        Assertions.assertThat(order.courierId).isEqualTo(courier.id)

    }

    @Test
    fun shouldCompleteOrderWhenStatusIsASSIGNED() {
        val order = Order(id = UUID.randomUUID(), location = Location(1, 1))
        val courier = Courier(
            name = "Василий",
            transport = Transport(name = "Car", speed = Speed(2)),
            _location = Location(1, 1)
        )

        order.assign(courier)
        order.complete()

        Assertions.assertThat(order.status).isEqualTo(OrderStatus.COMPLETED)
    }

    @Test
    fun shouldThrowIllegalStateExceptionWhenTryingToCompleteOrderNotAssigned() {
        val order = Order(id = UUID.randomUUID(), location = Location(1, 1))

        Assertions.assertThatThrownBy {
            order.complete()
        }.isInstanceOf(IllegalStateException::class.java)

    }
}
