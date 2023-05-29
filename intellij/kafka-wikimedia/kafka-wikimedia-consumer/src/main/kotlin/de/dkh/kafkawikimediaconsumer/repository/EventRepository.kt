package de.dkh.kafkawikimediaconsumer.repository

import de.dkh.kafkawikimediaconsumer.entity.Event
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : CrudRepository<Event, Long>, EventRepositoryCustom {

}