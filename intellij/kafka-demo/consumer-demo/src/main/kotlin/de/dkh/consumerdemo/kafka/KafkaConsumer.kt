package de.dkh.consumerdemo.kafka

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


/**
 * Kafka consumer service.
 * Receives messages from the topic {@code KafkaTopicConfig} using {@code KafkaListener}.
 * The Listener annotation means, that this consumer subscribes to the topic we have created.
 */
@Service
class KafkaConsumer {

    private val logger: Logger = LoggerFactory.getLogger(KafkaConsumer::class.java)

    @KafkaListener(topics = ["topic1"], groupId = "consumerGroup1")
    fun consume(message: String): String {
        val messageReceivedAlert = "Message about the customer $message received!"
        logger.info(messageReceivedAlert)
        return messageReceivedAlert
    }

}