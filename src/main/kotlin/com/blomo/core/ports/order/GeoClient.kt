package com.blomo.core.ports.order

import com.blomo.core.domain.shared.kernel.Location

interface GeoClient {
    fun findLocation(street: String): Location
}
