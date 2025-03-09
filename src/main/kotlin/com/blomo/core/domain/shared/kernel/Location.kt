package com.blomo.core.domain.shared.kernel

import kotlin.math.abs
import kotlin.random.Random

data class Location(
    val x: Int,
    val y: Int,
) {

    companion object {
        private const val MAX_X: Int = 10
        private const val MAX_Y: Int = 10
        private const val MIN_X: Int = 1
        private const val MIN_Y: Int = 1

        fun createRandom(): Location =
            Location(
                x = Random.nextInt(MIN_X, MAX_X + 1),
                y = Random.nextInt(MIN_Y, MAX_Y + 1),
            )

        fun validateCoordinates(x: Int, y: Int) {
            require(x in MIN_X..MAX_X) {
                "Coordinate x must be between $MIN_X and $MAX_X"
            }
            require(y in MIN_Y..MAX_Y) {
                "Coordinate y must be between $MIN_Y and $MAX_Y"
            }
        }

    }

    init {
        validateCoordinates(x, y)
    }

    fun distanceTo(other: Location): Int =
        abs(x - other.x) + abs(y - other.y)

}
