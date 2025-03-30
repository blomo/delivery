package com.blomo.core.application.usecases.queries.dto.courier

import com.blomo.core.domain.shared.kernel.Location
import java.util.*

data class BusyCourier(
    val courierId: UUID,
    val name: String,
    val location: Location,
    val transportId: UUID,
)
