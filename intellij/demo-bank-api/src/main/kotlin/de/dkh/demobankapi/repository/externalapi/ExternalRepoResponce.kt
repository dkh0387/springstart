package de.dkh.demobankapi.repository.externalapi

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.relational.core.mapping.Column

/**
 * The POJO for a external JSON responce.
 * This is an example of using external repositories (another endpoints).
 * NOTE: this {@code JsonProperty} annotation allows to explicitly point to the alias in the JSON being mapped to this object
 * (f.e. if the field calls other that the object property).
 */
data class ExternalRepoResponce(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    @JsonProperty("discountPercentage")
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: Collection<String>
)