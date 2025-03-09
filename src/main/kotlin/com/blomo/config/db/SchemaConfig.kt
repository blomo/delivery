package com.blomo.config.db

import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component
import java.sql.SQLException
import javax.sql.DataSource
// чтобы при запуске создавалась нужная схема для бд а не руками и вот интересно куда такой класс можно было вынести
@Component
class SchemaConfig(
    @Value("\${spring.liquibase.default-schema}")
    private val dbSchema: String,
) : BeanPostProcessor {

    private val validateSql = """^[a-zA-Z_][a-zA-Z0-9_]*$"""
    private val createSchemaSql = "CREATE SCHEMA IF NOT EXISTS $dbSchema"

    @Throws(BeansException::class)
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        return bean
    }

    @Throws(BeansException::class)
    override fun postProcessAfterInitialization(bean: Any, name: String): Any {
        if (bean is DataSource) {
            try {
                dbSchema.takeIf { it.matches(Regex(validateSql)) }
                    ?.let {
                        bean.connection.use { conn ->
                            conn.prepareStatement(createSchemaSql).use {
                                it.executeUpdate()
                            }
                        }
                    }
            } catch (e: SQLException) {
                throw RuntimeException("Failed to create schema", e)
            }
        }
        return bean
    }


}
