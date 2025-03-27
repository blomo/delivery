package com.blomo.infrastructure.adapters.psql.courier.mapper

import com.blomo.core.domain.aggregates.courier.Courier
import com.blomo.infrastructure.adapters.psql.common.mapper.toLocation
import com.blomo.infrastructure.adapters.psql.common.mapper.toLocationEntity
import com.blomo.infrastructure.adapters.psql.courier.entity.CourierEntity

fun Courier.toCourierEntity(): CourierEntity =
    CourierEntity(
        id = id,
        name = name,
        status = status,
        transport = transport.toEntity(),
        location = location.toLocationEntity(),
    )

fun CourierEntity.toCourier(): Courier =
    Courier.fromDb(
        id = id,
        name = name,
        status = status,
        transport = transport.toTransport(),
        location = location.toLocation(),
    )
