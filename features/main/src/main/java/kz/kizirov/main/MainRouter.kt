package kz.kizirov.main

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed class MainRouter : ScreenProvider {
    object OpenExampleScreen : MainRouter()
}