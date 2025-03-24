package com.blomo.core.domain.aggregates.courier

import com.blomo.core.domain.aggregates.order.OrderStatus

enum class CourierStatus(
    val id: Int, val description: String,
) {
    NOT_AVAILABLE(1, "NotAvailable"),
    FREE(2, "Ready"),
    BUSY(3, "Busy");

    fun isNotAvailable(status: CourierStatus): Boolean =
        NOT_AVAILABLE == status

    fun isFree(status: CourierStatus): Boolean =
        FREE == status

    fun isBusy(status: CourierStatus): Boolean =
        BUSY == status

    companion object {
        fun of(id: Int): OrderStatus? =
            OrderStatus.entries.firstOrNull { it.id == id }
    }

}
