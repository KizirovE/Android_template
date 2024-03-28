package kz.kizirov.core.network.models

import kotlinx.serialization.Serializable
import kz.kizirov.core.network.ParserHelper


@Serializable
data class ErrorModel(
    /*val successful: Boolean? = null,
    val message: MessageErrorModel? = null,
    val path: String? = null,*/
    val timestamp: String? = null,
    val status: Int? = null,
    val message: String? = null,
    val customMessage: String? = null,
    val error: String? = null,
    val error_description: String? = null,
)



/*
@Serializable
data class MessageErrorModel(val statusCode:Int,
                        val message: String? = null,
                        private val user_msg___ru: String? = null,
                        private val user_msg___kz:String? = null,
                        val error: String
                        ){
    val user_msg: String get() = ParserHelper.getActualLocalText(user_msg___kz, user_msg___ru)
    override fun toString(): String {
        return if(user_msg___ru != null && user_msg___kz != null) user_msg
        else if(message != null) message
        else error
    }
}*/