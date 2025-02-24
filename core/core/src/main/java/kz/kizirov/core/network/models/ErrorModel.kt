package kz.kizirov.core.network.models

import kotlinx.serialization.Serializable
import kz.kizirov.core.network.ParserHelper


@Serializable
data class ErrorModel(
    val timestamp: String? = null,
    val code: Int? = null,
    val url: String? = null,
    private val message: String? = null,
    private val customMessage: String? = null,
    private val error: String? = null,
    private val error_description: String? = null,
){
    val user_msg: String get() {
        var resultMessage = (url?:"")+"\n"
        resultMessage += code.toString()+"\n"
        resultMessage += message?:""
        resultMessage += customMessage?:""
        resultMessage += error?:""
        resultMessage += error_description?:""
        return resultMessage
    }
}