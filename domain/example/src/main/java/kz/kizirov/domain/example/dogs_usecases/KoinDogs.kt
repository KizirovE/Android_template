package kz.kizirov.domain.example.dogs_usecases

import org.koin.dsl.module



//Регистрируем в MyApp
val domainExampleModule = module {
    factory { GetAllDogsUseCase(get()) }
    factory { DeleteAllDogsUseCase(get()) }
    factory { LoadDog(get()) }
}