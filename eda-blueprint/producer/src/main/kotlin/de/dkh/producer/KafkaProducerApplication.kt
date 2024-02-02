package de.dkh.producer

import de.dkh.producer.kafka.Producer
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaProducerApplication(private val producer: Producer) :
    CommandLineRunner {
    override fun run(vararg args: String?) {
        producer.sendMessage()
        //this list contains all events from wikimedia collected during 10 minutes of request
        val eventData = producer.changesHandler
    }
}

fun main(args: Array<String>) {
    runApplication<KafkaProducerApplication>(*args)
}
