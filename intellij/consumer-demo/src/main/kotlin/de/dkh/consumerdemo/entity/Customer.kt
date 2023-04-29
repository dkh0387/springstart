package de.dkh.consumerdemo.entity

import lombok.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@ToString
@Table("customer")
data class Customer(@Id val id: Int, val firstName: String, val lastName: String, val email: String)
