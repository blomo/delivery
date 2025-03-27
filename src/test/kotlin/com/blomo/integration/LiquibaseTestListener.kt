package com.blomo.integration

import liquibase.integration.spring.SpringLiquibase
import org.springframework.test.context.TestContext
import org.springframework.test.context.support.AbstractTestExecutionListener

class LiquibaseTestListener : AbstractTestExecutionListener() {

    override fun afterTestMethod(testContext: TestContext) {
        recreateDatabase(testContext)
    }

    private fun recreateDatabase(testContext: TestContext) {
        val springLiquibase = testContext.applicationContext.getBean(SpringLiquibase::class.java)

        springLiquibase.isDropFirst = true
        springLiquibase.afterPropertiesSet()
    }

}
