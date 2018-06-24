package org.boot.services.metadata

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body


@Component
class MetadataApiHandler(@Autowired val repository: MetadataRepository) {

    private fun json() = ok().contentType(APPLICATION_JSON_UTF8)

    fun findAll(request: ServerRequest) = json().body(repository.findAll())

    fun findOne(request: ServerRequest) = json().body(repository.findByName(request.pathVariable("name")))

    fun create(request: ServerRequest) = json().body(repository.saveAll(request.bodyToMono(Metadata::class.java)))

}

