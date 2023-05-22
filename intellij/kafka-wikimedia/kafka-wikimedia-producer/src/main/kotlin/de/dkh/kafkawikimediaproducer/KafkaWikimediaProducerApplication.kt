package de.dkh.kafkawikimediaproducer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaWikimediaProducerApplication

fun main(args: Array<String>) {
    runApplication<KafkaWikimediaProducerApplication>(*args)
}
