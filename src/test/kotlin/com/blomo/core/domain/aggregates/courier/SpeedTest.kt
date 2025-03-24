package com.blomo.core.domain.aggregates.courier

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class SpeedTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    fun shouldCreateWhenSpeedCorrect(input: Int) {
        val result = Speed(value = input)

        Assertions.assertThat(result).isNotNull
        Assertions.assertThat(result.value).isEqualTo(input)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 4, -3, 55])
    fun shouldNotCreateWhenSpeedIsNotCorrect(input: Int) {
        Assertions.assertThatThrownBy {
            Speed(value = input)
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

}
