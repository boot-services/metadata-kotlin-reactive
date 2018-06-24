package org.boot.services.metadata

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document(collection = "metadata")
data class Metadata(val name: String, val value: String, @Id val id: String? = null)