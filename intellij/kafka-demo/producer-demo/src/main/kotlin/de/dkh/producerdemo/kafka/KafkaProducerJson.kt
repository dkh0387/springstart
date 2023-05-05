package de.dkh.producerdemo.kafka

import de.dkh.producerdemo.config.TOPIC_NAME
import de.dkh.producerdemo.entity.CustomerEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

/**
 * Example of Kafka producer sending a message using a {@code KafkaTemplate}.
 * This one is sending an entity object as value, since we defined:
 * {@code spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer}
 * in application.properties.
 */
@Service
class KafkaProducerJson(@Autowired private val kafkaTemplate: KafkaTemplate<String, CustomerEntity>) {

    val LOGGER: Logger = LoggerFactory.getLogger(KafkaProducerJson::class.java)

    /**
     * {@code MessageBuilder} translate the entity object into JSON and processes to the broker.
     * NOTE: the whole message being sent is customer as JSON!
     */
    @Throws(IllegalArgumentException::class)
    fun sendMessage(customerEntity: CustomerEntity): CustomerEntity {
        val message = MessageBuilder.withPayload(customerEntity)
            .setHeader(KafkaHeaders.TOPIC, TOPIC_NAME)
            .build()
        kafkaTemplate.send(message)
        LOGGER.info("MESSAGE FROM PRODUCER: $message")
        return message.payload
    }
}