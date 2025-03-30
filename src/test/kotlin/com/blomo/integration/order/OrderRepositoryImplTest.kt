package com.blomo.integration.order

import com.blomo.core.domain.aggregates.courier.Courier
import com.blomo.core.domain.aggregates.courier.Speed
import com.blomo.core.domain.aggregates.courier.Transport
import com.blomo.core.domain.aggregates.order.Order
import com.blomo.core.domain.shared.kernel.Location
import com.blomo.infrastructure.adapters.psql.order.OrderRepositoryImpl
import com.blomo.infrastructure.adapters.psql.order.entity.OrderEntity
import com.blomo.infrastructure.adapters.psql.order.repository.OrderRepositoryInfr
import com.blomo.integration.IntegrationTest
import java.util.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

@IntegrationTest
class OrderRepositoryImplTest {

    @Autowired
    private lateinit var orderRepositoryInfr: OrderRepositoryInfr

    @Autowired
    private lateinit var orderRepositoryImpl: OrderRepositoryImpl

    @BeforeEach
    fun setUp() {
        orderRepositoryInfr.deleteAll()
    }

    @Test
    fun add() {

        Assumptions.assumeTrue(orderRepositoryInfr.findAll().isEmpty())

        val order = Order(id = UUID.randomUUID(), location = Location(1, 1))

        orderRepositoryImpl.add(order)

        val result = orderRepositoryInfr.findByIdOrNull(order.id)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result?.id).isEqualTo(order.id)
    }

    @Test
    fun update() {

        Assumptions.assumeTrue(orderRepositoryInfr.findAll().isEmpty())

        val order = Order(id = UUID.randomUUID(), location = Location(1, 1))
        val courier =
            Courier(name = "Vasya", transport = Transport(name = "Car", speed = Speed(2)), _location = Location(1, 1))

        orderRepositoryImpl.add(order)
        order.assign(courier)
        orderRepositoryImpl.update(order)

        val result: OrderEntity? = orderRepositoryInfr.findByIdOrNull(order.id)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result?.id).isEqualTo(order.id)
        Assertions.assertThat(result?.courierId).isEqualTo(courier.id)
    }

    @Test
    fun getById() {

        Assumptions.assumeTrue(orderRepositoryInfr.findAll().isEmpty())
        val order = Order(id = UUID.randomUUID(), location = Location(1, 1))

        orderRepositoryImpl.add(order)


        val result: Order? = orderRepositoryImpl.getById(order.id)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result).isEqualTo(order)
    }

    @Test
    fun getAllByStatusAssigned() {

        Assumptions.assumeTrue(orderRepositoryInfr.findAll().isEmpty())
        val courier =
            Courier(name = "Vasya1", transport = Transport(name = "Car1", speed = Speed(2)), _location = Location(1, 1))
        val order1 = Order(id = UUID.randomUUID(), location = Location(1, 1))
        val order2 = Order(id = UUID.randomUUID(), location = Location(1, 1))
        val order3 = Order(id = UUID.randomUUID(), location = Location(1, 1))

        order2.assign(courier)

        orderRepositoryImpl.add(order1)
        orderRepositoryImpl.add(order3)
        orderRepositoryImpl.add(order2)


        val result: List<Order> = orderRepositoryImpl.getAllByStatusAssigned()

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result.size).isEqualTo(1)
    }

    @Test
    fun getAnyByStatusCreated() {

        Assumptions.assumeTrue(orderRepositoryInfr.findAll().isEmpty())
        val courier =
            Courier(name = "Vasya1", transport = Transport(name = "Car1", speed = Speed(2)), _location = Location(1, 1))
        val order1 = Order(id = UUID.randomUUID(), location = Location(1, 1))
        val order2 = Order(id = UUID.randomUUID(), location = Location(1, 1))
        val order3 = Order(id = UUID.randomUUID(), location = Location(1, 1))

        order2.assign(courier)

        orderRepositoryImpl.add(order1)
        orderRepositoryImpl.add(order3)
        orderRepositoryImpl.add(order2)


        val result: Order? = orderRepositoryImpl.getAnyByStatusCreated()

        Assertions.assertThat(result).isNotNull
    }
    
}
