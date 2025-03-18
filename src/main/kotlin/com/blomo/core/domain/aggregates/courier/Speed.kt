package com.blomo.core.domain.aggregates.courier

@JvmInline
value class Speed(val value: Int) {
    companion object {
        const val MIN = 1
        const val MAX = 3
    }

    init {
        require(value in MIN..MAX) { "Speed must be between $MIN and $MAX" }
    }
}
