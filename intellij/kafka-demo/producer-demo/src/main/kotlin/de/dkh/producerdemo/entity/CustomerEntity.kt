package de.dkh.producerdemo.entity

import lombok.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@ToString
@Table("customer")
data class CustomerEntity(@Id val id: Int, val firstName: String, val lastName: String, val email: String)
