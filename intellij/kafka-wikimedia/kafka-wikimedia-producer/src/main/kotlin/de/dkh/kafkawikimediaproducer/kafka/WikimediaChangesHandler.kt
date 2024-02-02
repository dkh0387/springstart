package de.dkh.kafkawikimediaproducer.kafka

import com.launchdarkly.eventsource.EventHandler
import com.launchdarkly.eventsource.MessageEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate

/**
 * NOTE: we need an older version of {@code implementation("com.launchdarkly:okhttp-eventsource:2.5.0")},
 * in the newest one the interface is no longer available.
 */
class WikimediaChangesHandler(private val kafkaTemplate: KafkaTemplate<String, String>, private val topic: String) :
    EventHandler {

    private val LOGGER: Logger = LoggerFactory.getLogger(WikimediaChangesHandler::class.java)
    private val messageEventData: ArrayList<String> = arrayListOf()

    /**
     * We only need to implement this one.
     * If an event will be sent this method will be triggered and read the wikipedia change event.
     * Then the data can be sent to the topic.
     */
    @Throws(Exception::class)
    override fun onMessage(event: String?, messageEvent: MessageEvent?) {
        if (messageEvent != null) {
            LOGGER.info("Event data --->>> ${messageEvent.data}")
            kafkaTemplate.send(topic, messageEvent.data)
            messageEventData.add(messageEvent.data)
        }
    }

    override fun onOpen() {
    }

    override fun onClosed() {
    }

    override fun onComment(comment: String?) {
    }

    override fun onError(t: Throwable?) {
    }
}