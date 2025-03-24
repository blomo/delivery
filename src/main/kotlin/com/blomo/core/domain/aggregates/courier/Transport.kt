package com.blomo.core.domain.aggregates.courier

import com.blomo.core.domain.shared.kernel.Location
import kotlin.math.abs
import java.util.*

class Transport(
    val name: String,
    val speed: Speed,
    val id: UUID = UUID.randomUUID()
) {

    init {
        require(name.isNotBlank()) { "Transport name cannot be blank" }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Transport) return false
        return this.id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    fun move(from: Location, to: Location): Location {

        val difX = to.x - from.x
        val difY = to.y - from.y

        var cruisingRange = speed.value

        val moveX = difX.coerceIn(-cruisingRange, cruisingRange)
        cruisingRange -= abs(moveX)

        val moveY = difY.coerceIn(-cruisingRange, cruisingRange)

        return Location(from.x + moveX, from.y + moveY)
    }
}
