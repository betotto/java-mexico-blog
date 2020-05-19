package javamexico.blog.utils

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object DataSource {

    val mysql = {
        val config = HikariConfig()
        var port = System.getenv("DB_PORT") ?: "3306"
        var host = System.getenv("DB_HOST") ?: "127.0.0.1"
        var dbName = System.getenv("DB_NAME") ?: "javamexico_blog"
        var dbUser = System.getenv("DB_USER") ?: "databaseowner"
        var dbPassword = System.getenv("DB_PASSWORD") ?: "123456"

        config.jdbcUrl = "jdbc:mysql://${host}:${port}/${dbName}?serverTimezone=UTC&useSSL=false"
        config.username = dbUser
        config.password = dbPassword
        config.driverClassName = "com.mysql.cj.jdbc.Driver"
        config.isAutoCommit = false
        config.maximumPoolSize = 5
        config.poolName = "blogJavaMexico"

        HikariDataSource(config)
    }()

}