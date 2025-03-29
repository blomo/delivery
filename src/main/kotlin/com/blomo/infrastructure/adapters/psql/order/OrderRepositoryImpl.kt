package com.blomo.infrastructure.adapters.psql.order

import com.blomo.core.domain.aggregates.order.Order
import com.blomo.core.domain.aggregates.order.OrderStatus
import com.blomo.core.ports.order.OrderRepository
import com.blomo.infrastructure.adapters.psql.order.entity.OrderEntity
import com.blomo.infrastructure.adapters.psql.order.mapper.toOrder
import com.blomo.infrastructure.adapters.psql.order.mapper.toOrderEntity
import com.blomo.infrastructure.adapters.psql.order.repository.OrderRepositoryInfr
import java.util.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class OrderRepositoryImpl(
    private val orderRepository: OrderRepositoryInfr,
) : OrderRepository {

    override fun add(order: Order) {
        orderRepository.save(
            order.toOrderEntity()
        )
    }

    override fun update(order: Order): Order =
        orderRepository
            .save(order.toOrderEntity())
            .toOrder()


    override fun getById(id: UUID): Order? =
        orderRepository
            .findByIdOrNull(id)
            ?.toOrder()

    override fun getAnyByStatusCreated(): Order? =
        orderRepository
            .findFirstByStatus(OrderStatus.CREATED)
            ?.toOrder()


    override fun getAllByStatusAssigned(): List<Order> =
        orderRepository
            .findAllByStatus(status = OrderStatus.ASSIGNED)
            .map(OrderEntity::toOrder)

}
