package com.blomo.integration.containers.postgres

import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext

class PostgresContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val postgresSqlDatabaseContainer = PostgresDbSpringContainer().apply { start() }

        applicationContext.beanFactory.registerSingleton(postgresSqlDatabaseContainer::class.simpleName!!, postgresSqlDatabaseContainer)
    }

}

class PostgresDbSpringContainer(
    postgresVersion: PostgresVersion = PostgresVersion.V_13_7_ALPINE,
    private val propertiesNames: PostgresPropertiesNames = PostgresPropertiesNames()
) : PostgresDbContainer(postgresVersion = postgresVersion) {
    override fun start() {
        super.start()

        System.setProperty(propertiesNames.urlParameterName, jdbcUrl)
        System.setProperty(propertiesNames.usernameParameterName, username)
        System.setProperty(propertiesNames.passwordParameterName, password)

    }

}

data class PostgresPropertiesNames(
    val urlParameterName: String = defaultUrlParameterName,
    val usernameParameterName: String = defaultUsernameParameterName,
    val passwordParameterName: String = defaultPasswordParameterName
) {
    internal companion object {
        internal const val defaultUrlParameterName: String = "spring.datasource.url"
        internal const val defaultUsernameParameterName: String = "spring.datasource.username"
        internal const val defaultPasswordParameterName: String = "spring.datasource.password"
    }

}
