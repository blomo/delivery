package com.blomo.core.domain.aggregates.courier

import com.blomo.core.domain.shared.kernel.Location
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class CourierTest {

    @Test
    fun shouldThrowIllegalArgumentExceptionWhenCourierNameIsBlank() {
        Assertions.assertThatThrownBy {
            Courier(name = "", transport = Transport(name = "Car", speed = Speed(2)), _location = Location(1, 1))
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldInitializeCourierWithCorrectNameAndStatus() {
        val transport = Transport(name = "Car", speed = Speed(2))
        val courier = Courier(name = "Василий", transport = transport, _location = Location(1, 1))

        Assertions.assertThat(courier.name).isEqualTo("Василий")
        Assertions.assertThat(courier.status).isEqualTo(CourierStatus.FREE)
    }

    @Test
    fun shouldChangeStatusToBusyWhenSetBusyIsCalled() {
        val transport = Transport(name = "Car", speed = Speed(2))
        val courier = Courier(name = "Василий", transport = transport, _location = Location(1, 1))

        courier.setBusy()

        Assertions.assertThat(courier.status).isEqualTo(CourierStatus.BUSY)
    }

    @Test
    fun shouldChangeStatusToFreeWhenSetFreeIsCalled() {
        val transport = Transport(name = "Car", speed = Speed(2))
        val courier = Courier(name = "Василий", transport = transport, _location = Location(1, 1))

        courier.setBusy()
        courier.setFree()

        Assertions.assertThat(courier.status).isEqualTo(CourierStatus.FREE)
    }

    @Test
    fun shouldMoveCourierToNewLocationWhenMoveIsCalled() {
        val transport = Transport(name = "Car", speed = Speed(2))
        val courier = Courier(name = "Василий", transport = transport, _location = Location(1, 1))
        val newLocation = Location(3, 5)

        courier.move(newLocation)

        Assertions.assertThat(courier.location).isEqualTo(Location(3,1))

    }

    @Test
    fun shouldCalculateTimeToLocationCorrectly() {
        val transport = Transport(name = "Car", speed = Speed(2))
        val courier = Courier(name = "Василий", transport = transport, _location = Location(1, 1))
        val targetLocation = Location(3, 5)

        val expectedTime = courier.calculateTimeToLocation(targetLocation)
        val expectedDistance = courier.location.distanceTo(targetLocation)

        Assertions.assertThat(expectedTime).isEqualTo(expectedDistance / 2.0f)

    }

}
