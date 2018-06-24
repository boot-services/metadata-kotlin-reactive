package org.boot.services.metadata

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MetadataApplication

fun main(args: Array<String>) {
    runApplication<MetadataApplication>(*args)
}


