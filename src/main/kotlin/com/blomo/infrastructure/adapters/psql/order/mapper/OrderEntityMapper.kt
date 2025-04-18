package com.blomo.infrastructure.adapters.psql.order.mapper

import com.blomo.core.domain.aggregates.order.Order
import com.blomo.infrastructure.adapters.psql.common.mapper.toLocation
import com.blomo.infrastructure.adapters.psql.common.mapper.toLocationEntity
import com.blomo.infrastructure.adapters.psql.order.entity.OrderEntity

fun Order.toOrderEntity(): OrderEntity =
    OrderEntity(
        id = id,
        status = status,
        courierId = courierId,
        location = location.toLocationEntity(),
    ).also {
        it.registerEvents(domainEvents)
        this.clearDomainEvents()
    }


fun OrderEntity.toOrder(): Order = Order.fromDb(
    id = id,
    status = status,
    location = location.toLocation(),
    courierId = courierId,
)
