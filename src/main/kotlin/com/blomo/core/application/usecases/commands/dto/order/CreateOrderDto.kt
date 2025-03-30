package com.blomo.core.application.usecases.commands.dto.order

import java.util.UUID

data class CreateOrderDto(
    val basketId : UUID,
    val street: String,
)
