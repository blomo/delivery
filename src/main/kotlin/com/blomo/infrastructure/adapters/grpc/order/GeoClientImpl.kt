package com.blomo.infrastructure.adapters.grpc.order

import com.blomo.core.domain.shared.kernel.Location
import com.blomo.core.ports.order.GeoClient
import geo.GeoGrpc
import geo.GeoOuterClass
import org.springframework.stereotype.Component

@Component
class GeoClientImpl(
    private val geoGrpcStub: GeoGrpc.GeoBlockingStub,
) : GeoClient {
    override fun findLocation(street: String) =
        geoGrpcStub
            .getGeolocation(
                GeoOuterClass.GetGeolocationRequest.newBuilder().setStreet(street).build(),
            ).location
            .run {
                Location(x, y)
            }
}
