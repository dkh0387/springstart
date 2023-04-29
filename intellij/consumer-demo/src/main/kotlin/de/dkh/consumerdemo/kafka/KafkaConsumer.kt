package de.dkh.consumerdemo.kafka

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


/**
 * Kafka consumer service.
 * Receives messages from the topic {@code KafkaTopicConfig} using {@code KafkaListener}.
 * The Listener annotation means, that this consumer subscribes to the topic we have created.
 */
@Service
@PropertySource("classpath:application.properties")
class KafkaConsumer(
    @Autowired
    private val environment: Environment
) {

    private val logger: Logger = LoggerFactory.getLogger(KafkaConsumer::class.java)
    //val groupId: String? = environment.getProperty("spring.kafka.consumer.group-id")

    @KafkaListener(topics = ["topic1"], groupId = "consumerGroup1")
    fun consume(message: String): String {
        val consumeMessageAlert = "Message about the customer $message received!"
        logger.info(consumeMessageAlert)
        return consumeMessageAlert
    }

}