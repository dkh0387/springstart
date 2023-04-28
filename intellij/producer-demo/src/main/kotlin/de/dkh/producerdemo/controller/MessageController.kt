package de.dkh.producerdemo.controller

import de.dkh.producerdemo.kafka.KafkaProducer
import de.dkh.producerdemo.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kafka/producer")
class MessageController(
    @Autowired private val kafkaProducer: KafkaProducer,
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
     */
    @GetMapping("/publish")
    fun publishMessage(@RequestParam("customerId") customerId: Int): ResponseEntity<String> {

        try {
            val customer = customerService.getById(customerId)
            val sentMessageAlert = kafkaProducer.sendMessage(customer)
            return ResponseEntity.ok(sentMessageAlert)
        } catch (exp: IllegalArgumentException) {
            throw IllegalArgumentException("Error by publishing infos for customer sending through Kafka producer!")
        }

    }
}