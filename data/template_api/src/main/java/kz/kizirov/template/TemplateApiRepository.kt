package kz.kizirov.template

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kz.kizirov.core.network.BaseApiClient
import kz.kizirov.core.network.KtorResponse
import kz.kizirov.template.db.entity.Dogs
import kz.kizirov.template.db.entity.DogsDB
import kz.kizirov.template.network.DogsDto
import kz.kizirov.template.network.dto.TemplateApiKtor

class TemplateApiRepository(
    private val api: TemplateApiKtor,
    private val db: DogsDB
    ): BaseApiClient() {

    suspend fun getDog(): KtorResponse<DogsDto> {
        return withContext(Dispatchers.IO) {
            safeApiCall {
                api.getDog()
            }
        }
    }

    fun getDogDB(): Flow<List<Dogs>> {
        return db.dogsDao().getAll()
    }

    suspend fun insertDogDB(dog: Dogs){
        return db.dogsDao().insertAll(dog)
    }

    suspend fun deleteAllDog(){
        return db.dogsDao().delete()
    }
}