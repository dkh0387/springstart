package de.dkh.consumer.service

import de.dkh.consumer.entity.Event
import de.dkh.consumer.repository.EventRepository
import org.springframework.stereotype.Service

@Service
class EventService(private val eventRepository: EventRepository) {

    fun save(event: Event) {
        eventRepository.save(event)
    }

}