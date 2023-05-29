package de.dkh.kafkawikimediaconsumer.service

import de.dkh.kafkawikimediaconsumer.entity.Event
import de.dkh.kafkawikimediaconsumer.repository.EventRepository
import org.springframework.stereotype.Service

@Service
class EventService(private val eventRepository: EventRepository) {

    fun save(event: Event) {
        eventRepository.save(event)
    }

}