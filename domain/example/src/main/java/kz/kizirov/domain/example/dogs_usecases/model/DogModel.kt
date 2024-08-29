package kz.kizirov.domain.example.dogs_usecases.model

import kz.kizirov.template.network.DogsDto

data class DogModel(
    val url: String,
    val status: String
)

fun DogsDto.toMap(): DogModel {
    return DogModel(message?:"", status?:"")
}