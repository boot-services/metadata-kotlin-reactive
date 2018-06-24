package org.boot.services.metadata

import io.kotlintest.Tags.Companion.Empty
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.body
import org.springframework.test.web.reactive.server.expectBodyList

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MetadataApplicationTests(@Autowired val client: WebTestClient, @Autowired val metadata: MetadataRepository) {

    @BeforeEach
    fun beforeEach(){
        metadata.deleteAll().block()
    }

	@Test
	fun `get all metadata`() {
        metadata.save(Metadata("firstName","Sunit")).block()
        metadata.save(Metadata("lastName","Parekh")).block()

        val result = client.get().uri("/api/metadata/").accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBodyList<Metadata>()
                .hasSize(2)
                .returnResult().responseBody!!

        result[0].name shouldBe "firstName"
        result[0].value shouldBe "Sunit"
        result[1].name shouldBe "lastName"
        result[1].value shouldBe "Parekh"
    }

    @Test
    fun `get a metadata`() {
        metadata.save(Metadata("office","thoughtworks")).block()

        val result = client.get().uri("/api/metadata/office").accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody(Metadata::class.java)
                .returnResult().responseBody!!

        result.value shouldBe "thoughtworks"
    }

    @Test
    fun `create metadata`(){
        val result = client.post().uri("/api/metadata/")
                .accept(APPLICATION_JSON).contentType(APPLICATION_JSON)
                .syncBody(Metadata("city", "pune"))
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBodyList<Metadata>()
                .hasSize(1)
                .returnResult().responseBody!!

        result[0].id shouldNotBe Empty
        result[0].name shouldBe "city"
        result[0].value shouldBe "pune"

    }

}
