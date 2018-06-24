package org.boot.services.metadata

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body

@Component
class MetadataApiHandler {

    @Autowired
    lateinit var metadata: MetadataRepository

    fun findAll(request: ServerRequest) = ok().contentType(APPLICATION_JSON_UTF8)
            .body(metadata.findAll())

    fun findOne(request: ServerRequest) = ok().contentType(APPLICATION_JSON_UTF8)
            .body(metadata.findByName(request.pathVariable("name")))

}

