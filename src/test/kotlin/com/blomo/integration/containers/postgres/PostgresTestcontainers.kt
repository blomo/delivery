package com.blomo.integration.containers.postgres

import org.junit.jupiter.api.extension.ExtendWith
import org.testcontainers.junit.jupiter.Testcontainers
import java.lang.annotation.Inherited

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited

@Testcontainers
@ExtendWith(PostgresTestcontainersExtension::class)
annotation class PostgresTestcontainers(
    val version: PostgresVersion = PostgresVersion.V_13_7_ALPINE,

    val urlParameterName: String = "",
    val usernameParameterName: String = "",
    val passwordParameterName: String = ""
)
