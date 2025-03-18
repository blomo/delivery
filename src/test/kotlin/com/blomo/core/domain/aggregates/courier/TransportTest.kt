package com.blomo.core.domain.aggregates.courier

import com.blomo.core.domain.shared.kernel.Location
import java.util.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class TransportTest {

    @Test
    fun shouldCreateTransportWithValidNameAndSpeed() {
        val transport = Transport(name = "Car", speed = Speed(2))

        Assertions.assertThat(transport).isNotNull
        Assertions.assertThat(transport.name).isEqualTo("Car")
        Assertions.assertThat(transport.speed.value).isEqualTo(2)
    }

    @Test
    fun shouldThrowExceptionWhenNameIsBlank() {
        Assertions.assertThatThrownBy {
            Transport(name = "", speed = Speed(2))
        }.isInstanceOf(IllegalArgumentException::class.java)

    }

    @Test
    fun shouldReturnTrueWhenTransportWithSameIdIsEqual() {
        val id = UUID.randomUUID()
        val transport1 = Transport("Car", Speed(2), id = id)
        val transport2 = Transport("Truck", Speed(2), id = id)

        Assertions.assertThat(transport1)
            .isEqualTo(transport2)
    }

    @Test
    fun shouldReturnFalseWhenTransportWithDifferentIdIsNotEqual() {
        val transport1 = Transport("Car", Speed(2))
        val transport2 = Transport("Truck", Speed(3))

        Assertions.assertThat(transport1).isNotEqualTo(transport2)
    }

    @Test
    fun shouldHaveSameHashCodeWhenTransportWithSameId() {
        val id = UUID.randomUUID()
        val transport1 = Transport("Car", Speed(2), id = id)
        val transport2 = Transport("Truck", Speed(2), id = id)

        Assertions.assertThat(transport1.hashCode()).isEqualTo(transport2.hashCode())
    }

    @Test
    fun shouldHaveDifferentHashCodeWhenTransportWithDifferentId() {
        val transport1 = Transport("Car", Speed(2))
        val transport2 = Transport("Truck", Speed(3))

        Assertions.assertThat(transport1.hashCode()).isNotEqualTo(transport2.hashCode())
    }

    @ParameterizedTest
    @CsvSource(
        "1, 1, 5, 5, 3, 1, 2", // from (1, 1) to (5, 5) should result in (3, 1) speed 2
        "1, 1, 1, 9, 1, 3, 2", // from (1, 1) to (1, 9) should result in (1, 3) speed 2
        "9, 9, 8, 8, 8, 8, 2", // from (9, 9) to (8, 8) should result in (8, 8) speed 2
        "9, 1, 1, 1, 7, 1, 2", // from (9, 1) to (1, 1) should result in (7, 1) speed 2
        "3, 3, 6, 3, 5, 3, 2",  // from (3, 3) to (6, 3) should result in (5, 3) speed 2
        "8, 8, 9, 9, 9, 9, 3",  // from (8, 8) to (9, 9) should result in (9, 3) speed 3
        "1, 3, 1, 1, 1, 2, 1",  // from (1, 3) to (1, 1) should result in (1, 2) speed 1
    )
    fun shouldMoveTransportCorrectly(
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int,
        expectedX: Int,
        expectedY: Int,
        speed: Int,
    ) {
        val transport = Transport(name = "Car", speed = Speed(speed))
        val fromLocation = Location(x = fromX, y = fromY)
        val toLocation = Location(x = toX, y = toY)

        val result: Location = transport.move(fromLocation, toLocation)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result).isEqualTo(Location(x = expectedX, y = expectedY))
    }

}
