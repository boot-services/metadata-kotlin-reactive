package org.boot.services.metadata

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class MetadataRoutes (val metadata: MetadataApiHandler) {

    @Bean
    fun apiRouter() =
            router {
                (accept(MediaType.APPLICATION_JSON) and "/api")
                .nest {
                    "/metadata".nest {
                        GET("/", metadata::findAll)
                        GET("/{name}", metadata::findOne)
                        POST("/", metadata::create)
                    }
                }
            }
}