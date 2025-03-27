package com.blomo.core.ports.courier

import com.blomo.core.domain.aggregates.courier.Courier
import java.util.*

interface CourierRepository {
    fun add(courier: Courier)
    fun update(courier: Courier): Courier
    fun getById(id: UUID): Courier?
    fun getAllByStatusFree(): List<Courier>
}
