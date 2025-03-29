package com.blomo.infrastructure.adapters.psql.courier.entity

import com.blomo.core.domain.aggregates.courier.CourierStatus
import com.blomo.infrastructure.adapters.psql.common.entity.LocationEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.ForeignKey
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "courier")
class CourierEntity(
    @Id
    val id: UUID,

    val name: String,

    @Enumerated(EnumType.STRING)
    val status: CourierStatus,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(
        foreignKey = ForeignKey(name = "courier_transport_id_fk"),
    )
    val transport: TransportEntity,

    @Embedded
    val location: LocationEntity,
)
