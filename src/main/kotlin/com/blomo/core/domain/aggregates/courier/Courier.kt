package com.blomo.core.domain.aggregates.courier

import com.blomo.core.domain.shared.kernel.Location
import java.util.*

class Courier(
    val name: String,
    val transport: Transport,
    private var _location: Location,
) {
    val id: UUID = UUID.randomUUID()

    var status: CourierStatus = CourierStatus.FREE
        private set

    val location: Location
        get() = _location


    init {
        require(name.isNotBlank()) { "Courier name cannot be blank" }
    }

    companion object {
        fun fromDb(
            id: UUID,
            name: String,
            transport: Transport,
            location: Location,
            status: CourierStatus
        ): Courier {
            val courier = Courier(name, transport, location)
            courier::class.java.getDeclaredField("id").apply {
                isAccessible = true
                set(courier, id)
            }
            courier.status = status
            return courier
        }
    }


    fun setFree() {
        this.status = CourierStatus.FREE
    }

    fun setBusy() {
        status = CourierStatus.BUSY
    }

    fun move(target: Location) {
        _location = transport.move(from = location, to = target)
    }

    fun calculateTimeToLocation(location: Location): Float =
        (_location.distanceTo(location) / transport.speed.value).toFloat()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Courier) return false
        return this.id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

}

