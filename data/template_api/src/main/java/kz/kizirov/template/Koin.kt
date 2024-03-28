package kz.kizirov.template

import org.koin.dsl.module

val dataTemplateApiModule = module {
    single { TemplateApiKtor(get()) }
    single { TemplateApiRepository(get()) }
}