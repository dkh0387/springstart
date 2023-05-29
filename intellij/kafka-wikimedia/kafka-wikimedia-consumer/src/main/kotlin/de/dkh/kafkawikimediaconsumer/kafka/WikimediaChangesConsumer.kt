package de.dkh.kafkawikimediaconsumer.kafka

import de.dkh.kafkawikimediaconsumer.entity.Event
import de.dkh.kafkawikimediaconsumer.service.EventService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class WikimediaChangesConsumer(private val eventService: EventService) {

    private val LOGGER: Logger = LoggerFactory.getLogger(WikimediaChangesConsumer::class.java)

    /**
     * This method actually listen to the topic and consume all data from it.
     * Further we can provide the data, f.e. save it in the db.
     * {@code eventMessage} parameter is the message coming from the topic.
     */
    @KafkaListener(topics = ["wikimedia_recentchange"], groupId = "consumerGroup1")
    fun consume(eventMessage: String) {

        LOGGER.info("Event message consumed from the topic: $eventMessage")
        val event = Event(0, eventMessage)
        eventService.save(event)
    }
}