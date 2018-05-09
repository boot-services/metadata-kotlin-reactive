package org.boot.services.metadata

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.test.test

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MetadataApplicationTests(@LocalServerPort port: Int) {

    private val client = WebClient.create("http://localhost:$port")

	@Test
	fun `get of hello URI should return Hello World!`() {
		client.get().uri("/api/welcome").accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono<Metadata>()
                .test()
                .consumeNextWith {
                    assertEquals("message", it.name)
                    assertEquals("welcome", it.value)
                }
                .verifyComplete()

	}

}
