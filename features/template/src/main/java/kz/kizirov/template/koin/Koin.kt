package kz.kizirov.template.koin

import kz.kizirov.template.example.ExampleViewModel
import org.koin.dsl.module


//Регистрируем в MyApp
val featureTemplateModule = module {
    factory { ExampleViewModel(get(), get(), get(), get()) }
}