package com.blomo.integration.containers.postgres

import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

open class PostgresDbContainer(postgresVersion: PostgresVersion = PostgresVersion.V_13_7_ALPINE) : KPostgreSQLContainer(
    DockerImageName.parse("postgres:${postgresVersion.tagName}")
        .asCompatibleSubstituteFor(IMAGE)
)

open class KPostgreSQLContainer(dockerImageName: DockerImageName) : PostgreSQLContainer<KPostgreSQLContainer>(dockerImageName) {

    companion object {
        const val JDBC_URL_TESTCONTAINERS_PROPERTY_NAME = "TESTCONTAINERS_JDBC_URL"
        const val USERNAME_TESTCONTAINERS_PROPERTY_NAME = "TESTCONTAINERS_USERNAME"
        const val PASSWORD_TESTCONTAINERS_PROPERTY_NAME = "TESTCONTAINERS_PASSWORD"

        val jdbcUrlSystemProperty: String
            get() = System.getProperty(JDBC_URL_TESTCONTAINERS_PROPERTY_NAME)

        val usernameSystemProperty: String
            get() = System.getProperty(USERNAME_TESTCONTAINERS_PROPERTY_NAME)

        val passwordSystemProperty: String
            get() = System.getProperty(PASSWORD_TESTCONTAINERS_PROPERTY_NAME)
    }

    override fun start() {
        super.start()

        System.setProperty(JDBC_URL_TESTCONTAINERS_PROPERTY_NAME, jdbcUrl)
        System.setProperty(USERNAME_TESTCONTAINERS_PROPERTY_NAME, username)
        System.setProperty(PASSWORD_TESTCONTAINERS_PROPERTY_NAME, password)
    }

}
