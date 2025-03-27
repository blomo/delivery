package com.blomo.integration.courier

import com.blomo.core.domain.aggregates.courier.Courier
import com.blomo.core.domain.aggregates.courier.CourierStatus
import com.blomo.core.domain.aggregates.courier.Speed
import com.blomo.core.domain.aggregates.courier.Transport
import com.blomo.core.domain.shared.kernel.Location
import com.blomo.infrastructure.adapters.psql.courier.CourierRepositoryImpl
import com.blomo.infrastructure.adapters.psql.courier.entity.CourierEntity
import com.blomo.infrastructure.adapters.psql.courier.repository.CourierRepositoryInfr
import com.blomo.integration.IntegrationTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

@IntegrationTest
class CourierRepositoryImplTest {

    @Autowired
    private lateinit var courierRepositoryInfr: CourierRepositoryInfr

    @Autowired
    private lateinit var courierRepositoryImpl: CourierRepositoryImpl

    @BeforeEach
    fun setUp() {
        courierRepositoryInfr.deleteAll()
    }

    @Test
    fun add() {

        Assumptions.assumeTrue(courierRepositoryInfr.findAll().isEmpty())
        val courier =
            Courier(name = "Vasya", transport = Transport(name = "Car", speed = Speed(2)), _location = Location(1, 1))

        courierRepositoryImpl.add(courier)

        val result = courierRepositoryInfr.findByIdOrNull(courier.id)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result?.id).isEqualTo(courier.id)
    }

    @Test
    fun update() {

        Assumptions.assumeTrue(courierRepositoryInfr.findAll().isEmpty())
        val courier =
            Courier(name = "Vasya", transport = Transport(name = "Car", speed = Speed(2)), _location = Location(1, 1))

        courierRepositoryImpl.add(courier)
        courier.setBusy()
        courierRepositoryImpl.update(courier)

        val result: CourierEntity? = courierRepositoryInfr.findByIdOrNull(courier.id)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result?.id).isEqualTo(courier.id)
        Assertions.assertThat(result?.status).isEqualTo(CourierStatus.BUSY)
    }

    @Test
    fun getBuId() {

        Assumptions.assumeTrue(courierRepositoryInfr.findAll().isEmpty())
        val courier =
            Courier(name = "Vasya", transport = Transport(name = "Car", speed = Speed(2)), _location = Location(1, 1))

        courierRepositoryImpl.add(courier)


        val result: Courier? = courierRepositoryImpl.getById(courier.id)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result).isEqualTo(courier)
    }

    @Test
    fun getAllByStatusFree() {

        Assumptions.assumeTrue(courierRepositoryInfr.findAll().isEmpty())
        val courier1 =
            Courier(name = "Vasya1", transport = Transport(name = "Car1", speed = Speed(2)), _location = Location(1, 1))
        val courier2 =
            Courier(name = "Vasya2", transport = Transport(name = "Car2", speed = Speed(2)), _location = Location(1, 1))
        val courier3 =
            Courier(name = "Vasya3", transport = Transport(name = "Car3", speed = Speed(2)), _location = Location(1, 1))

        courier2.setBusy()

        courierRepositoryImpl.add(courier1)
        courierRepositoryImpl.add(courier2)
        courierRepositoryImpl.add(courier3)


        val result: List<Courier> = courierRepositoryImpl.getAllByStatusFree()

        Assertions.assertThat(result).isNotEmpty
        Assertions.assertThat(result.size).isEqualTo(2)
    }
}
