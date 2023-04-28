package de.dkh.producerdemo.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

const val TOPIC_NAME = "topic1"

@Configuration
class KafkaTopicConfig {

    @Bean
    fun newTopic(): NewTopic = TopicBuilder.name(TOPIC_NAME).replicas(3).build()
}