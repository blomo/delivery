package com.blomo.infrastructure.adapters.psql.courier.mapper

import com.blomo.core.domain.aggregates.courier.Speed
import com.blomo.core.domain.aggregates.courier.Transport
import com.blomo.infrastructure.adapters.psql.courier.entity.TransportEntity

fun Transport.toEntity(): TransportEntity = TransportEntity(
    id = id,
    name = name,
    speed = speed.value
)


fun TransportEntity.toTransport(): Transport = Transport(
    name = name,
    speed = Speed(speed),
    id = id
)
