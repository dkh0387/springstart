package de.dkh.consumerdemo.controller

import de.dkh.consumerdemo.kafka.KafkaConsumer
import de.dkh.consumerdemo.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kafka/consumer")
class MessageController(
    @Autowired private val kafkaConsumer: KafkaConsumer,
    @Autowired private val customerService: CustomerService
) {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleNotFound(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("/index")
    fun showIndex(): String = "Hello World!"

    /**
     * The message coming from {@code KafkaProducer#sendMessage()} is being published as REST:
     * {@code http://localhost:8081/kafka/producer/publish?customerId=...}
     * Doing:
     * 1. {@code KafkaProducer} sends a message using {@code KafkaTemplate}
     * 2. This message is being passed into GET request of this REST endpoint and shown in browser
     *
     * To see the topic within Kafka broker run:
     * `kafka-topics --describe --topic topic1 --bootstrap-server kafka-service:29092` in zookeeper bash
     * In order to verify events=messages being sent to the topic run:
     * `kafka-console-consumer --topic  topic1 --from-beginning --bootstrap-server kafka-service:29092` in zookeeper bash.
     */
    @GetMapping("/receive")
    fun consumeMessage(@RequestParam("message") message: String): ResponseEntity<String> {

        try {
            val consumeMessageAlert = kafkaConsumer.consume(message)
            return ResponseEntity.ok(consumeMessageAlert)
        } catch (exp: IllegalArgumentException) {
            throw IllegalArgumentException("Error by receiving infos for customer from Kafka broker!")
        }

    }
}