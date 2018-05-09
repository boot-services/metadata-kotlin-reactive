package org.boot.services.metadata

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.reactive.function.client.WebClient
import reactor.test.test

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MetadataApplicationTests(@LocalServerPort port: Int) {

    private val client = WebClient.create("http://localhost:$port")

	@Test
	fun `get all metadata`() {
		client.get().uri("/api/metadata/").accept(APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Metadata::class.java)
                .test()
                .consumeNextWith {
                    assertEquals("name", it.name)
                    assertEquals("sunit", it.value)
                }
                .consumeNextWith {
                    assertEquals("city", it.name)
                    assertEquals("pune", it.value)
                }
                .verifyComplete()

	}

    @Test
    fun `get a metadata`() {
        client.get().uri("/api/metadata/1234").accept(APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Metadata::class.java)
                .test()
                .consumeNextWith {
                    assertEquals("1234", it.name)
                    assertEquals("sunit", it.value)
                }
                .verifyComplete()

    }

}
