package com.blomo.integration.containers.postgres

import java.lang.annotation.Inherited
import org.junit.jupiter.api.extension.ExtendWith

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited

@Repeatable

@ExtendWith(PostgresTestcontainersExtension::class)
annotation class PostgresContainer(
    val version: PostgresVersion = PostgresVersion.V_13_7_ALPINE,

    val urlParameterName: String = "",
    val usernameParameterName: String = "",
    val passwordParameterName: String = "",
)
