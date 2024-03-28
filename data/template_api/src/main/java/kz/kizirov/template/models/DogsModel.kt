package kz.kizirov.template.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogsModel(
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: String? = null
)