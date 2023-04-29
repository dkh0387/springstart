package de.dkh.consumerdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProducerDemoApplication

fun main(args: Array<String>) {
    runApplication<ProducerDemoApplication>(*args)
}
