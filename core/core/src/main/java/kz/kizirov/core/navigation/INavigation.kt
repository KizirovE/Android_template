package kz.kizirov.core.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

interface INavigation {
    /**
     * Инициализация навигации
     * MainActivity
     *val navigation = get<INavigation>()
     *Navigator(
     *  screen = MainScreen(),
     *  content = { navigator ->
     *      navigation.init(navigator)
     *      ...
     *  }
     * )
     */
    fun init(navigator: Navigator)

    fun pop()

    fun push(screen: Screen)

    fun replaceAll(screen: Screen)

    /*onBackPressed = {
      navigation.canBackPressed()
    }*/
    fun canBackPressed():Boolean

    fun onBack(result:Any)
}