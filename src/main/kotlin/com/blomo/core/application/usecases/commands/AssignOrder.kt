package com.blomo.core.application.usecases.commands

import com.blomo.core.domain.services.DispatchService
import com.blomo.core.ports.courier.CourierRepository
import com.blomo.core.ports.order.OrderRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AssignOrder(
    private val courierRepository: CourierRepository,
    private val orderRepository: OrderRepository,
    private val dispatchService: DispatchService,
) {

    @Transactional
    fun execute() {
        orderRepository
            .getAnyByStatusCreated()
            ?.let { order ->
                val couriers = courierRepository.getAllByStatusFree()
                dispatchService.dispatch(order, couriers)?.also { courier ->
                    courierRepository.update(courier)
                    orderRepository.update(order)
                }
            }
    }
}
