package kz.kizirov.template

import cafe.adriel.voyager.core.registry.ScreenRegistry
import kz.kizirov.core.CoreApp
import kz.kizirov.core.network.ktor.httpClientModule
import kz.kizirov.core.storage.GlobalStorage
import kz.kizirov.template.modules.dataModules
import kz.kizirov.template.modules.dbModules
import kz.kizirov.template.modules.domainModules
import kz.kizirov.template.modules.featureModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: CoreApp() {

    override fun onCreate() {
        super.onCreate()

        GlobalStorage.setBaseUrl(BuildConfig.BASE_URL)
        GlobalStorage.setFlavor(BuildConfig.FLAVOR)
        GlobalStorage.applicationId = BuildConfig.APPLICATION_ID

        ScreenRegistry{
            featureMain()
        }

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(httpClientModule)
            modules(dbModules)
            modules(dataModules)
            modules(domainModules)
            modules(featureModules)
        }
    }
}
