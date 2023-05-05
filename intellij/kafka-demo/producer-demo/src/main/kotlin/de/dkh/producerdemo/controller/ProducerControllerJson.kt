package de.dkh.producerdemo.controller

import de.dkh.producerdemo.entity.CustomerEntity
import de.dkh.producerdemo.kafka.KafkaProducerJson
import de.dkh.producerdemo.service.CustomerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/kafka/producerjson")
class ProducerControllerJson(
    @Autowired private val kafkaProducerJson: KafkaProducerJson
) {

    val LOGGER: Logger = LoggerFactory.getLogger(ProducerControllerJson::class.java)

    @ExceptionHandler(Exception::class)
    fun handleNotFound(e: Exception): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @PostMapping("/publishjson")
    fun publishMessageJson(@RequestBody customerEntity: CustomerEntity): ResponseEntity<String> {

        try {
            val customerEntityJson = kafkaProducerJson.sendMessage(customerEntity)
            return ResponseEntity.ok("JSON message for the customer: $customerEntityJson sent!")
        } catch (exp: Exception) {
            val message = "Error by publishing infos for customer sending through Kafka producer!"
            LOGGER.error(message)
            throw Exception(message)
        }

    }
}