package de.dkh.consumer.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("event")
data class Event(@Id val id:Long, private val eventMessage: String)
