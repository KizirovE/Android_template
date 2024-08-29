package kz.kizirov.template.modules

import kz.kizirov.main.koin.featureMainModule
import kz.kizirov.template.koin.featureTemplateModule

val featureModules = listOf(
    featureTemplateModule,
    featureMainModule,
)