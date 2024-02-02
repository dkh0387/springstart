package de.dkh.kafkawikimedia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaWikimediaApplication

fun main(args: Array<String>) {
    runApplication<KafkaWikimediaApplication>(*args)
}
