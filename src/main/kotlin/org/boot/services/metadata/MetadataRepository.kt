package org.boot.services.metadata

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface MetadataRepository: ReactiveCrudRepository<Metadata, String> {

    fun findByName(name: String): Mono<Metadata>
}