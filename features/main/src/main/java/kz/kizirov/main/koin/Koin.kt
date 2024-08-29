package kz.kizirov.main.koin

import kz.kizirov.main.MainViewModel
import org.koin.dsl.module

val featureMainModule = module {
    factory { MainViewModel(get()) }
}