package de.dkh.producer.kafka

import com.launchdarkly.eventsource.EventHandler
import com.launchdarkly.eventsource.EventSource
import de.dkh.producer.config.KafkaTopicConfig
import lombok.Getter
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.net.URI
import java.util.concurrent.TimeUnit
import kotlin.jvm.Throws

/**
 * We are reading a real time stream data from https://stream.wikimedia.org/v2/stream/recentchange,
 * so we use event source here.
 */
@Service
class Producer(private val kafkaTemplate: KafkaTemplate<String, String>) {

    @Getter
    val changesHandler: EventHandler =
        ChangesHandler(kafkaTemplate, KafkaTopicConfig.TOPIC_NAME)

    /**
     * 1. Prepare event handler for sending data through kafka template to the topic
     * 2. Create event source, which reads data from URI, and start them in a separate thread.
     *    After a unit of time (10 minutes) it ends reading events from wikimedia.
     *
     * NOTE: we are not calling {@code WikimediaChangesHandler.onMessage(event: String?, messageEvent: MessageEvent?)}
     * explicitly. This method is being triggered any time we read events from URI.
     */
    @Throws(Exception::class)
    fun sendMessage() {

        val eventSource = EventSource.Builder(
            changesHandler,
            URI.create("https://stream.wikimedia.org/v2/stream/recentchange")
        ).build()

        eventSource?.start()

        TimeUnit.MINUTES.sleep(10)
    }

}