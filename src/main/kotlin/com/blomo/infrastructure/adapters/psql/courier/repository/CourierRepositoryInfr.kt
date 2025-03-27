package com.blomo.infrastructure.adapters.psql.courier.repository

import com.blomo.core.domain.aggregates.courier.CourierStatus
import com.blomo.infrastructure.adapters.psql.courier.entity.CourierEntity
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository

interface CourierRepositoryInfr : JpaRepository<CourierEntity, UUID> {
    fun getAllByStatus(status: CourierStatus = CourierStatus.FREE): List<CourierEntity>
}
