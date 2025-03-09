package com.blomo.core.domain.shared.kernel

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class LocationTest {

    @Test
    fun shouldCreateWhenAllCoordinatesCorrect() {
        val result = Location(x = 5, y = 7)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result.x).isEqualTo(5)
        Assertions.assertThat(result.y).isEqualTo(7)
    }

    @Test
    fun shouldNotCreateWhenOneOfCoordinatesIsNotCorrect() {
        Assertions.assertThatThrownBy {
            Location(x = 5, y = 17)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun shouldEqualWhenCoordinatesAreEqual() {
        val currentLocation = Location(x = 5, y = 7)
        val targetLocation = Location(x = 5, y = 7)

        val result: Boolean = currentLocation == targetLocation

        Assertions.assertThat(result).isTrue
    }

    @Test
    fun shouldCalculateDistanceBetweenLocations() {
        val currentLocationDummy = Location(x = 5, y = 7)
        val targetLocationDummy = Location(x = 2, y = 9)

        val result: Int = currentLocationDummy.distanceTo(targetLocationDummy)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result).isEqualTo(5)

    }

    @Test
    fun shouldCalculateDistanceWhenCurrentLocationEqualTarget() {
        val currentLocationDummy = Location(x = 5, y = 7)
        val targetLocationDummy = Location(x = 5, y = 7)

        val result: Int = currentLocationDummy.distanceTo(targetLocationDummy)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result).isEqualTo(0)
    }

    @Test
    fun shouldCreateRandomLocation() {
        val result: Location = Location.createRandom()

        Assertions.assertThat(result).isNotNull
    }

}
