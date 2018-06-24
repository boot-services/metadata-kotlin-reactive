package org.boot.services.metadata

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body

@Component
class MetadataApiHandler(@Autowired val metadata: MetadataRepository) {

    fun findAll(request: ServerRequest) = json().body(metadata.findAll())

    fun findOne(request: ServerRequest) = json().body(metadata.findByName(request.pathVariable("name")))

    private fun json() = ok().contentType(APPLICATION_JSON_UTF8)

}

