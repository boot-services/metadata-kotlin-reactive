package org.boot.services.metadata

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBodyList

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MetadataApplicationTests(@Autowired val client: WebTestClient) {

	@Test
	fun `get all metadata`() {
        val result = client.get().uri("/api/metadata/").accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBodyList<Metadata>()

        result.hasSize(2)
        val list = result.returnResult().responseBody ?: throw Error("Empty list")

        list[0].name shouldBe "city"
        list[0].value shouldBe "pune"
        list[1].name shouldBe "office"
        list[1].value shouldBe "thoughtworks"
    }

    @Test
    fun `get a metadata`() {
        val result = client.get().uri("/api/metadata/office").accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody(Metadata::class.java)

        val meta = result.returnResult().responseBody ?: throw Error("Empty result")
        meta.value shouldBe "thoughtworks"
    }

}
