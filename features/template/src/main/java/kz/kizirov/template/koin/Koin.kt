package kz.kizirov.template.koin

import kz.kizirov.template.ExampleViewModel
import org.koin.dsl.module


//Регистрируем в MyApp
val featureTemplateModule = module {
    factory { ExampleViewModel(get(), get(), get()) }
}