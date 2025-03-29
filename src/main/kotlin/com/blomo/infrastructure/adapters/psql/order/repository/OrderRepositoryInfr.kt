package com.blomo.infrastructure.adapters.psql.order.repository

import com.blomo.core.domain.aggregates.order.OrderStatus
import com.blomo.infrastructure.adapters.psql.order.entity.OrderEntity
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository


interface OrderRepositoryInfr : JpaRepository<OrderEntity, UUID> {
    fun findFirstByStatus(status: OrderStatus): OrderEntity?
    fun findAllByStatus(status: OrderStatus): List<OrderEntity>
}
