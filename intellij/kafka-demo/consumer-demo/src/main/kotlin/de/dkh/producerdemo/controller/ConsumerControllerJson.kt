package de.dkh.producerdemo.controller

import de.dkh.producerdemo.entity.CustomerEntity
import de.dkh.producerdemo.kafka.KafkaConsumer
import de.dkh.producerdemo.kafka.KafkaConsumerJson
import de.dkh.producerdemo.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Consumer controller receiving JSON object from POJO being sent by producer.
 */
@RestController
@RequestMapping("/kafka/consumerjson")
class ConsumerControllerJson(
    @Autowired private val kafkaConsumerJson: KafkaConsumerJson,
    @Autowired private val customerService: CustomerService
) {
    @ExceptionHandler(Exception::class)
    fun handleNotFound(e: Exception): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    /**
     * The message coming from {@code KafkaProducer#sendMessage()} is being received:
     * {@code http://localhost:8081/kafka/producer/publish?customerId=...}
     * Doing:
     * 1. {@code KafkaConsumer} receives a message using {@code KafkaListener} annotation (listening to the same topic)
     * 2. This message is being passed into GET request of this REST endpoint and shown in browser
     *
     * @TODO: extract the received customer and persist in the database
     */
    @GetMapping("/receivejson")
    fun receiveMessageJson(@RequestParam("firstname") firstName: String): ResponseEntity<String> {

        try {
            val customerEntity = customerService.getByFirstName(firstName)
            val customerEntityReceived = customerEntity?.let { kafkaConsumerJson.consume(it) }
            if (customerEntityReceived != null) {
                customerService.save(customerEntityReceived)
            }
            return ResponseEntity.ok(customerEntity.toString())
        } catch (exp: IllegalArgumentException) {
            throw IllegalArgumentException("Error by publishing infos for customer sending through Kafka producer!")
        }

    }
}