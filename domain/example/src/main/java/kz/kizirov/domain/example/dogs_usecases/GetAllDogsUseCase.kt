package kz.kizirov.domain.example.dogs_usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import kz.kizirov.core.domain.ResultUC
import kz.kizirov.domain.example.dogs_usecases.model.DogModel
import kz.kizirov.template.TemplateApiRepository

class GetAllDogsUseCase(
    private val repository: TemplateApiRepository
) {
    suspend operator fun invoke(): ResultUC<Flow<List<DogModel>>> {
        return withContext(Dispatchers.IO) {
            ResultUC(repository.getDogDB().transform {
                emit(it.map { dog ->
                    DogModel(dog.message ?: "", dog.status ?: "")
                })
            }, null)
        }
    }
}