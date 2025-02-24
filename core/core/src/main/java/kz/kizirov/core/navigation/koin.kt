package kz.kizirov.core.navigation

import org.koin.dsl.module

val navigationModule = module {
    single<INavigation> { Navigation() }
}