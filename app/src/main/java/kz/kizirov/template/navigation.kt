package kz.kizirov.template

import cafe.adriel.voyager.core.registry.screenModule
import kz.kizirov.main.MainRouter
import kz.kizirov.template.example.ExampleScreen


val featureMain = screenModule {
    register<MainRouter.OpenExampleScreen> {
        ExampleScreen()
    }
}
