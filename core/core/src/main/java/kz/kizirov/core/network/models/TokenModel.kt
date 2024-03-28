package kz.kizirov.core.network.models.auth

@kotlinx.serialization.Serializable
data class TokenModel(
    val access_token: String,
    val refresh_token: String,
)