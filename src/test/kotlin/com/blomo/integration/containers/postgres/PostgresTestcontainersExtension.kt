package com.blomo.integration.containers.postgres

import com.blomo.integration.containers.postgres.PostgresPropertiesNames.Companion.defaultPasswordParameterName
import com.blomo.integration.containers.postgres.PostgresPropertiesNames.Companion.defaultUrlParameterName
import com.blomo.integration.containers.postgres.PostgresPropertiesNames.Companion.defaultUsernameParameterName
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils

class PostgresTestcontainersExtension : BeforeAllCallback {
    override fun beforeAll(context: ExtensionContext) {
        val annotation = AnnotationUtils.findAnnotation(context.element, PostgresTestcontainers::class.java)
        val annotationsRepeatable = AnnotationUtils.findRepeatableAnnotations(context.element, PostgresContainer::class.java)

        if (annotation.isPresent) {
            val a = annotation.get()
            PostgresDbSpringContainer(
                postgresVersion = a.version,
                propertiesNames = PostgresPropertiesNames(
                    urlParameterName = a.urlParameterName.takeIf { it.isNotBlank() } ?: defaultUrlParameterName,
                    usernameParameterName = a.usernameParameterName.takeIf { it.isNotBlank() } ?: defaultUsernameParameterName,
                    passwordParameterName = a.passwordParameterName.takeIf { it.isNotBlank() } ?: defaultPasswordParameterName
                )
            ).start()
        } else if (annotationsRepeatable.isNotEmpty()) {
            annotationsRepeatable.forEach { a ->
                PostgresDbSpringContainer(
                    postgresVersion = a.version,
                    propertiesNames = PostgresPropertiesNames(
                        urlParameterName = a.urlParameterName.takeIf { it.isNotBlank() } ?: defaultUrlParameterName,
                        usernameParameterName = a.usernameParameterName.takeIf { it.isNotBlank() } ?: defaultUsernameParameterName,
                        passwordParameterName = a.passwordParameterName.takeIf { it.isNotBlank() } ?: defaultPasswordParameterName
                    )
                ).start()
            }
        } else {
            PostgresDbSpringContainer().start()
        }

    }

}
