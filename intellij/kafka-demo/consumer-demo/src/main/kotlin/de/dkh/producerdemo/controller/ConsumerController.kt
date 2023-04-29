package de.dkh.producerdemo.controller

import de.dkh.producerdemo.kafka.KafkaConsumer
import de.dkh.producerdemo.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kafka/consumer")
class ConsumerController(
    @Autowired private val kafkaConsumer: KafkaConsumer,
    @Autowired private val customerService: CustomerService
) {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleNotFound(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("/index")
    fun showIndex(): String = "Hello World!"

    /**
     * The message coming from {@code KafkaProducer#sendMessage()} is being received:
     * {@code http://localhost:8081/kafka/producer/publish?customerId=...}
     * Doing:
     * 1. {@code KafkaConsumer} receives a message using {@code KafkaListener} annotation (listening to the same topic)
     * 2. This message is being passed into GET request of this REST endpoint and shown in browser
     *
     * @TODO: extract the received customer and persist in the database
     */
    @GetMapping("/receive")
    fun publishMessage(@RequestParam("message") message: String): ResponseEntity<String> {

        try {
            val messageReceivedAlert = kafkaConsumer.consume(message)
            return ResponseEntity.ok(messageReceivedAlert)
        } catch (exp: IllegalArgumentException) {
            throw IllegalArgumentException("Error by publishing infos for customer sending through Kafka producer!")
        }

    }
}