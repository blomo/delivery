package com.blomo.infrastructure.adapters.psql.order.entity

import com.blomo.core.domain.aggregates.order.OrderStatus
import com.blomo.core.domain.shared.kernel.Location
import com.blomo.infrastructure.adapters.psql.common.entity.LocationEntity
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "order_table")
class OrderEntity(
    @Id
    val id: UUID,

    @Enumerated(EnumType.STRING)
    val status: OrderStatus,

    @Column(nullable = true)
    val courierId: UUID?,

    @Embedded
    val location: LocationEntity,
)
