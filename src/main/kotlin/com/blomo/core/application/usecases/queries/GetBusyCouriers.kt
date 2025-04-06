package com.blomo.core.application.usecases.queries

import com.blomo.core.application.usecases.queries.dto.courier.BusyCourier
import com.blomo.core.ports.courier.CourierRepository
import org.springframework.stereotype.Component

@Component
class GetBusyCouriers(
    private val courierRepository: CourierRepository,
) {

    fun execute(): List<BusyCourier> =
        courierRepository.getAllBusy()
}
