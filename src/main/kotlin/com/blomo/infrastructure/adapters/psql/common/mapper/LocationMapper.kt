package com.blomo.infrastructure.adapters.psql.common.mapper

import com.blomo.core.domain.shared.kernel.Location
import com.blomo.infrastructure.adapters.psql.common.entity.LocationEntity

fun Location.toLocationEntity(): LocationEntity = LocationEntity(
    x = x,
    y = y
)

fun LocationEntity.toLocation(): Location = Location(
    x = x,
    y = y
)
