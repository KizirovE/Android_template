package kz.kizirov.template

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kz.kizirov.core.network.BaseApiClient
import kz.kizirov.core.network.KtorResponse
import kz.kizirov.template.models.DogsModel

class TemplateApiRepository(private val api: TemplateApiKtor): BaseApiClient() {

    suspend fun getDog(): KtorResponse<DogsModel> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                api.getDog()
            }
        }
    }
}