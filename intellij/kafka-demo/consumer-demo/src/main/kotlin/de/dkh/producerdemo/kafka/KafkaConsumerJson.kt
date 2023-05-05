package de.dkh.producerdemo.kafka

import de.dkh.producerdemo.entity.CustomerEntity
import de.dkh.producerdemo.service.CustomerService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import kotlin.jvm.Throws


/**
 * Kafka consumer service consuming JSON.
 * Receives messages from the topic {@code KafkaTopicConfig} using {@code KafkaListener}.
 * The Listener annotation means, that this consumer subscribes to the topic we have created.
 */
@Service
class KafkaConsumerJson(@Autowired private val customerService: CustomerService) {

    val LOGGER: Logger = LoggerFactory.getLogger(KafkaConsumerJson::class.java)

    @KafkaListener(topics = ["topic1"], groupId = "consumerGroup1")
    @Throws(Exception::class)
    fun consume(customerEntity: CustomerEntity): CustomerEntity {
        LOGGER.info("MESSAGE FROM CONSUMER: $customerEntity")

        try {
            customerService.save(customerEntity)
        } catch (e: Exception) {
            LOGGER.error(e.localizedMessage)
        }
        return customerEntity
    }

}