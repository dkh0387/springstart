package de.dkh.kafkawikimediaconsumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan(basePackages = ["de.dkh.kafkawikimediaconsumer.entity"])
class KafkaWikimediaConsumerApplication

fun main(args: Array<String>) {
    runApplication<KafkaWikimediaConsumerApplication>(*args)
}
