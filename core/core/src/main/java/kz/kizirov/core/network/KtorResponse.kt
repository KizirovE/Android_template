package kz.kizirov.core.network

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import kz.kizirov.core.network.models.ErrorModel


data class KtorResponse<T>(
    private val status: Status,
    val url: String?,
    private val _body: T?,
    val error: ErrorModel? = null,
){

    companion object {
        suspend inline fun <reified T> success(data: HttpResponse): KtorResponse<T> {
            return KtorResponse(
                status = Status.Success,
                _body = data.body<T>(),
                error = null,
                url = data.request.url.toString(),
            )
        }

        fun <T> failure(code: Int, url:String, exception: Exception): KtorResponse<T> {
            return KtorResponse(
                status = Status.Failure,
                _body = null,
                error = generateErrorModel(code, url, exception),
                url = url
            )
        }
        suspend inline fun <reified T> failure(data: HttpResponse): KtorResponse<T> {
            return KtorResponse(
                status = Status.Failure,
                _body = null,
                error = data.body<ErrorModel>().copy(url = data.request.url.toString()),
                url = data.request.url.toString()
            )
        }

        private fun generateErrorModel(code: Int, url: String, exception: Exception): ErrorModel {
            return createErrorModel(code, exception.toString(), url)
        }

        private fun createErrorModel(code: Int = 0, customMessage: String, url: String): ErrorModel {
            return ErrorModel(
                code = code,
                message = null,
                url = url,
                customMessage = customMessage?: ""
            )
        }
    }

    val failed: Boolean get() = this.status == Status.Failure

    val isSuccessful: Boolean get() = !failed

    val body get() = _body!!

    sealed class Status {
        object Success : Status()
        object Failure : Status()
    }
}