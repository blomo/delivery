package com.blomo.integration

import com.blomo.integration.containers.postgres.PostgresContainerInitializer
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import java.lang.annotation.Inherited


/**
 * Аннотация, объединяющая в себе логику:
 * - Поднятие веб-контекста на рандомном порту
 * - Мокирование механизма аутентификации
 * - Инициализации TestContainers
 * - Очищение базы данных и пересоздания контекста Liquibase (после каждого теста)
 *
 * @see LiquibaseTestListener
 * @see PostgresContainerInitializer
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited

@TestExecutionListeners(
    value = [LiquibaseTestListener::class],
    mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@ContextConfiguration(
    initializers = [
        PostgresContainerInitializer::class,
    ]
)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("junit")
@AutoConfigureMockMvc
annotation class IntegrationTest
