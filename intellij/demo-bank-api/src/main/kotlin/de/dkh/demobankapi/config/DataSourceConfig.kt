package de.dkh.demobankapi.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.transaction.PlatformTransactionManager
import java.util.logging.Logger
import javax.sql.DataSource


@Configuration
@EnableJdbcRepositories(basePackages = ["de.dkh.demobankapi.repository"])
@PropertySource("classpath:application.properties")
class DataSourceConfig(val environment: Environment) : AbstractJdbcConfiguration() {

    //private val LOG: Logger = LoggerFactory.getLogger()

    @Bean("dataSource")
    fun h2DataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(environment["spring.datasource.driver-class-name"]!!)
        dataSource.url = environment["spring.datasource.url"]!!
        dataSource.username = environment["spring.datasource.username"]!!
        dataSource.password = environment["spring.datasource.password"]!!
        return dataSource
    }

    @Bean
    fun namedParameterJdbcOperations(dataSource: DataSource): NamedParameterJdbcOperations {
        return NamedParameterJdbcTemplate(dataSource)
    }

    @Bean
    fun transactionManager(dataSource: DataSource): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource)
    }
}