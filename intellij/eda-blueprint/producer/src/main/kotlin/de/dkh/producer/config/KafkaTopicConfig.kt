package de.dkh.producer.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopicConfig {

    companion object {
        const val TOPIC_NAME = "wikimedia_recentchange"
    }

    @Bean
    fun topic(): NewTopic = TopicBuilder.name(TOPIC_NAME).build()
}