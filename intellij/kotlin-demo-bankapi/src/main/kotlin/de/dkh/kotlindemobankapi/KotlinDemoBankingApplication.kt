package de.dkh.kotlindemobankapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinDemoBankingApplication

fun main(args: Array<String>) {
    runApplication<KotlinDemoBankingApplication>(*args)
}
