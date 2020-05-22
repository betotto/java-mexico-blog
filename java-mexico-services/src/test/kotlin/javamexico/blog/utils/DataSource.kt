package javamexico.blog.utils

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object DataSource {
    val mysql = {
        val databaseScript = javaClass.classLoader.getResource("populate.sql").path
        val config = HikariConfig()

        config.jdbcUrl = "jdbc:h2:mem:javamexico;INIT=CREATE SCHEMA IF NOT EXISTS javamexico\\;RUNSCRIPT FROM '${databaseScript}'"
        config.username = "sa"
        config.driverClassName = "org.h2.Driver"
        config.isAutoCommit = false
        config.maximumPoolSize = 1
        config.poolName = "testJavaMexico"

        HikariDataSource(config)
    }()
}
