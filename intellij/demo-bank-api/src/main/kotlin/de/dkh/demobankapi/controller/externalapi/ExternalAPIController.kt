package de.dkh.demobankapi.controller.externalapi

import de.dkh.demobankapi.repository.externalapi.ExternalAPIRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/external/api")
class ExternalAPIController(@Autowired private val externalAPIRepository: ExternalAPIRepository) {

    @GetMapping("/dummy-json/{id}")
    fun fetchProductById(@PathVariable id: Int): String = externalAPIRepository.fetchObjectById(id)
}