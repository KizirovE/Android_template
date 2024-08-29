package kz.kizirov.template

import kz.kizirov.template.network.dto.TemplateApiKtor
import org.koin.dsl.module

val dataTemplateApiModule = module {
    single { TemplateApiKtor(get()) }
    single { TemplateApiRepository(get(), get()) }
}