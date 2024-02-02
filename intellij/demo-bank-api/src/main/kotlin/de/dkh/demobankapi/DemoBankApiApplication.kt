package de.dkh.demobankapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
class DemoBankApiApplication {
    /**
     * We need to provide a bean for {@code RestTemplate},
     * otherwise it can not been autowired in {@code ExternalAPIRepository}.
     */
    @Bean
    fun createRestTemplateBean(builder: RestTemplateBuilder): RestTemplate = builder.build()
}

fun main(args: Array<String>) {
    runApplication<DemoBankApiApplication>(*args)
}
