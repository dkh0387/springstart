package de.dkh.demomicroserviceskafka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoMicroservicesKafkaApplication {

}

fun main(args: Array<String>) {
    runApplication<DemoMicroservicesKafkaApplication>(*args)
}
