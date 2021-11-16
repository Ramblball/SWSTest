package database.utils

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import org.slf4j.LoggerFactory
import java.util.*

fun initDB(testing: Boolean) {
    val properties = Properties()
    properties.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource")
    properties.setProperty("dataSource.user", System.getenv("POSTGRES_USER"))
    properties.setProperty("dataSource.password", System.getenv("POSTGRES_PASSWORD"))
    if (testing) {
        properties.setProperty("dataSource.databaseName", System.getenv("POSTGRES_TEST_DB"))
        properties.setProperty("dataSource.schema", "test")
    } else {
        properties.setProperty("dataSource.databaseName", System.getenv("POSTGRES_DB"))
        properties.setProperty("dataSource.schema", "public")
    }
    properties.setProperty("dataSource.portNumber", System.getenv("POSTGRES_PORT"))
    properties.setProperty("dataSource.serverName", System.getenv("POSTGRES_HOST_ALIAS"))
    val dbConfig = HikariConfig(properties)
    val dataSource = HikariDataSource(dbConfig)
    Database.connect(dataSource)
    LoggerFactory.getLogger(Application::class.simpleName).info("Initialized Database")
}