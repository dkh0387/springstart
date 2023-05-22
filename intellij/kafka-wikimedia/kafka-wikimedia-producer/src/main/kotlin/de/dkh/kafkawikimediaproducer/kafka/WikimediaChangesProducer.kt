package de.dkh.kafkawikimediaproducer.kafka

import com.launchdarkly.eventsource.EventHandler
import com.launchdarkly.eventsource.EventSource
import de.dkh.kafkawikimediaproducer.config.KafkaTopicConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.net.URI
import java.util.concurrent.TimeUnit

/**
 * We are reading a real time stream data from https://stream.wikimedia.org/v2/stream/recentchange,
 * so we use event source here.
 */
@Service
class WikimediaChangesProducer(private val kafkaTemplate: KafkaTemplate<String, String>) {

    val LOGGER: Logger = LoggerFactory.getLogger(WikimediaChangesProducer::class.java)

    /**
     * 1. Prepare event handler for sending data through kafka template to the topic
     * 2. Create event source, which reads data from URI, and start them in a separate thread.
     *    After a unit of time (10 minutes) it ends reading events from wikimedia.
     *
     * NOTE: we are not calling {@code WikimediaChangesHandler.onMessage(event: String?, messageEvent: MessageEvent?)}
     * explicitly. This method is being triggered any time we read events from URI.
     */
    fun sendMessage() {

        val wikimediaChangesHandler: EventHandler =
            WikimediaChangesHandler(kafkaTemplate, KafkaTopicConfig.TOPIC_NAME)

        EventSource.Builder(
            wikimediaChangesHandler,
            URI.create("https://stream.wikimedia.org/v2/stream/recentchange")
        ).build()?.start()

        TimeUnit.MINUTES.sleep(10)
    }

}