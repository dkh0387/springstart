package de.dkh.producerdemo.kafka

import de.dkh.producerdemo.config.TOPIC_NAME
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * Kafka producer service.
 * Sends messages to the topic {@code KafkaTopicConfig} using {@code KafkaTemplate}.
 */
@Service
class KafkaProducer(
    /*
     * Kafka template to use for sending messages to the topic.
     * NOTE: we have defined key/value (de)serializer being String/String!
     */
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    private val logger: Logger = LoggerFactory.getLogger(KafkaProducer::class.java)

    /**
     * Example of sending a message to the defined topic.
     * NOTE: the {@code NewTopic} singelton is being created in {@code KafkaTopicConfig}.
     */
    fun sendMessage(messsage: String) {
        logger.info("Message sent: $messsage")
        kafkaTemplate.send(TOPIC_NAME, messsage)
    }


}