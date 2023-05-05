package de.dkh.producerdemo.kafka

import de.dkh.producerdemo.entity.CustomerEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


/**
 * Kafka consumer service consuming JSON.
 * Receives messages from the topic {@code KafkaTopicConfig} using {@code KafkaListener}.
 * The Listener annotation means, that this consumer subscribes to the topic we have created.
 */
@Service
class KafkaConsumerJson {

    @KafkaListener(topics = ["topic1"], groupId = "consumerGroup1")
    fun consume(customerEntity: CustomerEntity): CustomerEntity = customerEntity

}