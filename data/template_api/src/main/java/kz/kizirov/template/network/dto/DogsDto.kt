package kz.kizirov.template.network


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DogsDto(
    @SerialName("message")
    val message: String? = null,
    @SerialName("status")
    val status: String? = null
)
