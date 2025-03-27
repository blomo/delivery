package com.blomo.infrastructure.adapters.psql.common.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class LocationEntity(
    @Column(name = "location_x", nullable = false)
    val x: Int,
    @Column(name = "location_y", nullable = false)
    val y: Int,
)
