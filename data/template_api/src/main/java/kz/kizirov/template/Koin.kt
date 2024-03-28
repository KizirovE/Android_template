package kz.kizirov.template

import org.koin.dsl.module

val templateApiModule = module {
    single { TemplateApiKtor(get()) }
    single { TemplateApiRepository(get()) }
}