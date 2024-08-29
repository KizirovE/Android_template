package kz.kizirov.domain.example.dogs_usecases

import kz.kizirov.core.domain.ResultUC
import kz.kizirov.template.TemplateApiRepository

class DeleteAllDogsUseCase(
    private val repository: TemplateApiRepository
) {
    suspend operator fun invoke(): ResultUC<Boolean> {
        repository.deleteAllDog()
        return ResultUC<Boolean>(true, null)
    }
}