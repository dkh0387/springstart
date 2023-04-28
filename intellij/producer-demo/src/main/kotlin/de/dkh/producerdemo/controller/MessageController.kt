package de.dkh.producerdemo.controller

import de.dkh.producerdemo.kafka.KafkaProducer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kafka/producer")
class MessageController(private val kafkaProducer: KafkaProducer) {

    @ExceptionHandler(Exception::class)
    fun handleNotFound(e: Exception): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("/index")
    fun showIndex(): String = "Hello World!"

    /**
     * The message coming from {@code KafkaProducer#sendMessage()} is being published as REST:
     * {@code http://localhost:8081/kafka/producer/publish?message=...}
     * Doing:
     * 1. {@code KafkaProducer} sends a message using {@code KafkaTemplate}
     * 2. This message is being passed into GET request of this REST endpoint and shown in browser
     */
    @GetMapping("/publish")
    fun publishMessage(@RequestParam("message") message: String): ResponseEntity<String> {

        try {
            val sentMessageAlert = kafkaProducer.sendMessage(message)
            return ResponseEntity.ok(sentMessageAlert)
        } catch (exp: Exception) {
            throw Exception("Error by publishing a message sending through Kafka producer!")
        }

    }
}