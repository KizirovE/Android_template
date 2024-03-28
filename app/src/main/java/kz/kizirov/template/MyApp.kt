package kz.kizirov.template

import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.registry.screenModule
import kz.kizirov.core.CoreApp
import kz.kizirov.core.network.ktor.httpClientModule
import kz.kizirov.core.storage.GlobalStorage
import kz.kizirov.main.MainRouter
import kz.kizirov.main.koin.featureMainModule
import kz.kizirov.template.koin.featureTemplateModule
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
            modules(
                httpClientModule,
                templateApiModule,

                featureTemplateModule,
                featureMainModule,
                )
        }
    }
}

val featureMain = screenModule {
    register<MainRouter.OpenExampleScreen> {
        ExampleScreen()
    }
}
