package de.dkh.producerdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConsumerDemoApplication

fun main(args: Array<String>) {
    runApplication<ConsumerDemoApplication>(*args)
}
