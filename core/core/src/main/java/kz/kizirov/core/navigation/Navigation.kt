package kz.kizirov.core.navigation

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import kz.kizirov.core.base.CoreBaseScreen
import kz.kizirov.core.navigation_compose.ResultNavigation


class Navigation: INavigation {

    private lateinit var _navigator: Navigator
    private val navigator get() = _navigator

    override fun init(navigator: Navigator){
        if(::_navigator.isInitialized && _navigator.hashCode() == navigator.hashCode()) {
            return
        }
        _navigator = navigator
    }

    override fun pop(){
        (navigator.lastItem as CoreBaseScreen).isCanCloseScreen{
            if(it){
                navigator.pop()
            }
        }
    }

    override fun push(screen: Screen){
        navigator.push(screen)
    }

    override fun replaceAll(screen: Screen){
        (navigator.lastItem as CoreBaseScreen).isCanCloseScreen{
            if(it){
                navigator.replaceAll(screen)
            }
        }
    }

    override fun canBackPressed():Boolean {
        //отключаем дефолтный onBackPressed передав ему false
        //в каждом экране чекаем можем ли мы его закрыть, по дефолту тру если функция не переопределена
        //если функция переопределена, то может быть показано алерт с вопросом Закрыть, да/нет,
        //поэтому вручную делаем navigator.pop(), потому что onBackPressed не будет ждать резльтат лямбды
        (navigator.lastItem as CoreBaseScreen).isCanCloseScreen(
            onCanCloseScreen = {
                if(it) {
                    navigator.pop()
                }
            }
        )
        return false
    }

    override fun onBack(result:Any){
        ResultNavigation.setValue(result)
    }
}