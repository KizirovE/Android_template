package kz.kizirov.domain.example.dogs_usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kz.kizirov.core.domain.BaseUseCase
import kz.kizirov.core.domain.ResultUC
import kz.kizirov.domain.example.dogs_usecases.model.DogModel
import kz.kizirov.domain.example.dogs_usecases.model.toMap
import kz.kizirov.template.TemplateApiRepository
import kz.kizirov.template.db.entity.Dogs
import kz.kizirov.template.network.DogsDto
import trikita.log.Log

class LoadDog(
    private val repository: TemplateApiRepository
): BaseUseCase() {

    suspend operator fun invoke(): ResultUC<Boolean> {
        return withContext(Dispatchers.IO) {
            val job1 = async { return@async repository.getDog() }
            val job2 = async { return@async repository.getDog() }
            job1.await().apply {
                if(isSuccessful){
                    repository.insertDogDB(Dogs(null, body.message, body.status))
                }
            }

            job2.await().apply {
                if(isSuccessful){
                    repository.insertDogDB(Dogs(null, body.message, body.status))
                }
            }
            /*val res = result<DogsDto, DogModel>(
                {
                    job1.await()
                },
                toMap = { body ->
                    body.toMap()
                }
            )

            val res2 = result<DogsDto, DogModel>(
                {
                    job2.await()
                },
                toMap = { body ->
                    body.toMap()
                }
            )


            repository.insertDogDB(Dogs(null, res2.body.url, res2.body.status))
            repository.insertDogDB(Dogs(null, body.message, body.status))
                    */

            return@withContext ResultUC(true, null)
        }
    }
}