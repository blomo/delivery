package com.blomo.infrastructure.adapters.grpc.order

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties("application.infrastructure.grpc")
data class GrpcProperties(
    val host: String,
    val port: Int,
)
