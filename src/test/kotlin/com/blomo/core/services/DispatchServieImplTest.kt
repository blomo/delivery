package com.blomo.core.services

import com.blomo.core.domain.aggregates.courier.Courier
import com.blomo.core.domain.aggregates.courier.Speed
import com.blomo.core.domain.aggregates.courier.Transport
import com.blomo.core.domain.aggregates.order.Order
import com.blomo.core.domain.shared.kernel.Location
import java.util.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DispatchServieImplTest {
    private val dispatchServie = DispatchServieImpl()

    @Test
    fun shouldAssignFirstFreeCourierBasedOnTime() {
        val transport = Transport(name = "Car", speed = Speed(2))
        val courier = Courier(name = "Василий", transport = transport, _location = Location(1, 1))
        val transport1 = Transport(name = "Bicycle", speed = Speed(1))
        val courier1 = Courier(name = "Петр", transport = transport1, _location = Location(4, 8))
        val order = Order(UUID.randomUUID(), location = Location(8, 8))


        val couriers = listOf(courier, courier1)
        val assignedCourier: Courier? = dispatchServie.dispatch(order, couriers)

        Assertions.assertThat(assignedCourier).isEqualTo(courier1)
    }

    @Test
    fun shouldNullIfNoFreeCouriersAvailable() {
        val transport = Transport(name = "Car", speed = Speed(2))
        val courier = Courier(name = "Василий", transport = transport, _location = Location(1, 1))
        val transport1 = Transport(name = "Bicycle", speed = Speed(1))
        val courier1 = Courier(name = "Петр", transport = transport1, _location = Location(4, 8))
        val order = Order(UUID.randomUUID(), location = Location(8, 8))
        courier.setBusy()
        courier1.setBusy()

        val couriers = listOf(courier1, courier)
        val assignedCourier: Courier? = dispatchServie.dispatch(order, couriers)

        Assertions.assertThat(assignedCourier).isNull()
    }

    @Test
    fun shouldNullIfCouriersListEmpty() {
        val order = Order(UUID.randomUUID(), location = Location(8, 8))

        val assignedCourier: Courier? = dispatchServie.dispatch(order, listOf())

        Assertions.assertThat(assignedCourier).isNull()
    }

}
