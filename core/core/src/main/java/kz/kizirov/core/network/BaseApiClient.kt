package kz.kizirov.core.network

import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request

open class BaseApiClient() {
    protected suspend inline fun <reified T> safeApiCall(apiCall: () -> HttpResponse): KtorResponse<T> {
        var url = ""
        var code = 0
        return try {
            val response = apiCall.invoke()
            url = response.request.url.toString()
            code = response.status.value
            if(response.status.value in 200..299){
                KtorResponse.success(response)
            }else {
                KtorResponse.failure<T>(response)
            }
        } catch (e: Exception) {
            KtorResponse.failure(code,url, e)
        }
    }
}