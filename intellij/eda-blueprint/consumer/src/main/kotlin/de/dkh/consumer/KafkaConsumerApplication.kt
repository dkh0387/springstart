package de.dkh.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(basePackages = ["de.dkh.consumer.entity"])
class KafkaConsumerApplication

fun main(args: Array<String>) {
    runApplication<KafkaConsumerApplication>(*args)
}
