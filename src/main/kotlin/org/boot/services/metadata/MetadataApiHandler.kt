package org.boot.services.metadata

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class MetadataApiHandler {

    fun welcome(serverRequest: ServerRequest) = ok()
                    .body(Mono.just(Metadata("message","welcome")), Metadata::class.java)
}

