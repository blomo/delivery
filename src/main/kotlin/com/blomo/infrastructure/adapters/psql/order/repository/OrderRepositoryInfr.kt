package com.blomo.infrastructure.adapters.psql.order.repository

import com.blomo.core.application.usecases.queries.dto.order.NotCompletedOrder
import com.blomo.core.domain.aggregates.order.OrderStatus
import com.blomo.infrastructure.adapters.psql.order.entity.OrderEntity
import java.util.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface OrderRepositoryInfr : JpaRepository<OrderEntity, UUID> {
    fun findFirstByStatus(status: OrderStatus): OrderEntity?
    fun findAllByStatus(status: OrderStatus): List<OrderEntity>

    @Query(
        """
        SELECT new com.blomo.core.application.usecases.queries.dto.order.NotCompletedOrder(
            o.id,
            o.location.x,
            o.location.y,
            o.status
        )   
        FROM OrderEntity o 
        WHERE o.status <> 'COMPLETED'
        """
    )
    fun findAllNotCompleted(): List<NotCompletedOrder>
}
