package org.boot.services.metadata

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class MetadataApiHandler {

    fun findAll(request: ServerRequest) = ok()
                    .body(Flux.just(Metadata("name","sunit"),Metadata("city","pune")), Metadata::class.java)

    fun findOne(request: ServerRequest) = ok()
            .body(Mono.just(Metadata(request.pathVariable("id"),"sunit")), Metadata::class.java)

}

