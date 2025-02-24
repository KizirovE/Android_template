package kz.kizirov.template.start_screen

import cafe.adriel.voyager.core.screen.Screen
import kz.kizirov.core.storage.GlobalStorage
import kz.kizirov.main.MainScreen
import kz.kizirov.template.BuildConfig

class StartScreenImpl: StartScreen {
    override fun getStartScreen(): Screen {
        return if(BuildConfig.FLAVOR == "dev"){
            if(GlobalStorage.getAuthToken() == null) {
                MainScreen()
            } else {
                MainScreen()
            }
        }else{
            if(GlobalStorage.getAuthToken() == null) MainScreen() else MainScreen()
        }
    }
}