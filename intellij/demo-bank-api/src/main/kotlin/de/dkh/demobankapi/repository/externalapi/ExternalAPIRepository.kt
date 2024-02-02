package de.dkh.demobankapi.repository.externalapi

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

import org.springframework.web.client.getForEntity
import java.io.IOException

@Repository("externalAPIRepository")
class ExternalAPIRepository(@Autowired private val restTemplate: RestTemplate) {

    fun fetchObjectById(id: Int): String {
        val responseEntity: ResponseEntity<ExternalRepoResponce> =
            restTemplate.getForEntity<ExternalRepoResponce>("https://dummyjson.com/products/1")

        // body is in this case of type ExternalRepoResponce and we call Getter
        return responseEntity.body?.category ?: throw IOException("Could not fetch data!")
    }
}


