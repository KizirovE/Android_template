package kz.kizirov.core.domain

import io.ktor.client.statement.HttpResponse
import kz.kizirov.core.network.KtorResponse

open class BaseUseCase() {
    protected suspend inline fun <reified T, reified E> result(response: () -> KtorResponse<T>, noinline toMap:(T)-> E): ResultUC<E> {
        val r = response.invoke()
        return ResultUC<E>(
            if(r.isSuccessful) toMap(r.body) else null,
            if(r.failed) ResultUC.Failed(r.error?.message?:"") else null
        )
    }
}