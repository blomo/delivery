package com.blomo.infrastructure.adapters.grpc.order


import geo.GeoGrpc
import geo.GeoGrpc.GeoBlockingStub
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GrpcConfig(
    private val properties: GrpcProperties,
) {
    @Bean
    fun grpcChannel(): ManagedChannel =
        ManagedChannelBuilder
            .forAddress(properties.host, properties.port)
            .usePlaintext()
            .build()

    @Bean
    fun grpcStub(): GeoBlockingStub = GeoGrpc.newBlockingStub(grpcChannel())
}
