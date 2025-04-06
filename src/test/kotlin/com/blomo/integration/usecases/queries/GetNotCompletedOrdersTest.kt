package com.blomo.integration.usecases.queries

import com.blomo.core.application.usecases.queries.GetNotCompletedOrders
import com.blomo.core.domain.aggregates.courier.Courier
import com.blomo.core.domain.aggregates.courier.Speed
import com.blomo.core.domain.aggregates.courier.Transport
import com.blomo.core.domain.aggregates.order.Order
import com.blomo.core.domain.shared.kernel.Location
import com.blomo.infrastructure.adapters.psql.order.mapper.toOrderEntity
import com.blomo.infrastructure.adapters.psql.order.repository.OrderRepositoryInfr
import com.blomo.integration.IntegrationTest
import java.util.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@IntegrationTest
class GetNotCompletedOrdersTest {

    @Autowired
    private lateinit var orderRepositoryInfr: OrderRepositoryInfr

    @Autowired
    private lateinit var getNotCompletedOrders: GetNotCompletedOrders

    @BeforeEach
    fun setUp() {
        orderRepositoryInfr.deleteAll()
    }

    @Test
    fun getAllNotCompletedOrders() {
        val courier =
            Courier(name = "Vasya", transport = Transport(name = "Car", speed = Speed(2)), _location = Location(1, 1))

        val order1 = Order(id = UUID.randomUUID(), location = Location(1, 1))
        order1.assign(courier)

        val order2 = Order(id = UUID.randomUUID(), location = Location(2, 1))

        val order3 = Order(id = UUID.randomUUID(), location = Location(3, 1))
        order3.assign(courier)
        order3.complete()

        val order4 = Order(id = UUID.randomUUID(), location = Location(4, 1))


        orderRepositoryInfr.saveAll(
            listOf(
                order1.toOrderEntity(),
                order2.toOrderEntity(),
                order3.toOrderEntity(),
                order4.toOrderEntity()
            )
        )

        val orders = getNotCompletedOrders.execute()

        Assertions.assertThat(orders.size).isEqualTo(3)
        Assertions.assertThat(order3.id).isNotIn(orders.map { it.orderId })


    }
}
