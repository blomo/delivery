package com.blomo.infrastructure.adapters.psql.courier

import com.blomo.core.domain.aggregates.courier.Courier
import com.blomo.core.domain.aggregates.courier.CourierStatus
import com.blomo.core.ports.courier.CourierRepository
import com.blomo.infrastructure.adapters.psql.courier.entity.CourierEntity
import com.blomo.infrastructure.adapters.psql.courier.mapper.toCourier
import com.blomo.infrastructure.adapters.psql.courier.mapper.toCourierEntity
import com.blomo.infrastructure.adapters.psql.courier.repository.CourierRepositoryInfr
import java.util.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Component
@Transactional
class CourierRepositoryImpl(
    private val courierRepository: CourierRepositoryInfr,
) : CourierRepository {

    override fun add(courier: Courier) {
        courierRepository.save(
            courier.toCourierEntity()
        )
    }

    override fun update(courier: Courier): Courier =
        courierRepository.save(
            courier.toCourierEntity()
        ).toCourier()

    override fun getById(id: UUID): Courier? =
        courierRepository
            .findByIdOrNull(id)
            ?.toCourier()

    override fun getAllByStatusFree(): List<Courier> =
        courierRepository
            .getAllByStatus(status = CourierStatus.FREE)
            .map(CourierEntity::toCourier)

}
