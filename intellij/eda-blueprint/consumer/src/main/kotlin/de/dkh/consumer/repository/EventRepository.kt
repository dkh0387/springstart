package de.dkh.consumer.repository

import de.dkh.consumer.entity.Event
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : CrudRepository<Event, Long>, EventRepositoryCustom {

}