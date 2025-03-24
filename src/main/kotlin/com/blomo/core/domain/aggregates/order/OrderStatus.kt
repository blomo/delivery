package com.blomo.core.domain.aggregates.order


enum class OrderStatus(
    val id: Int, val description: String,
) {
    CREATED(1, "Created"),
    ASSIGNED(2, "Assigned"),
    COMPLETED(3, "Completed");

    fun isAssigned(status: OrderStatus): Boolean =
        ASSIGNED == status

    companion object {
        fun of(id: Int): OrderStatus? =
            entries.firstOrNull { it.id == id }
    }


}

