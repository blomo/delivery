package com.blomo.infrastructure.adapters.psql.courier.mapper

import com.blomo.core.application.usecases.queries.dto.courier.BusyCourier
import com.blomo.infrastructure.adapters.psql.common.mapper.toLocation
import com.blomo.infrastructure.adapters.psql.courier.entity.CourierEntity

fun CourierEntity.toBusyCourier(): BusyCourier = BusyCourier(
    courierId = id,
    name = name,
    location = location.toLocation(),
    transportId = transport.id
)
