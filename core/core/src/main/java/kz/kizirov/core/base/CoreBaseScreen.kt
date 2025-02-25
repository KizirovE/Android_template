package kz.kizirov.core.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import kz.alseco.core.base.AlertType
import kz.kizirov.core.getActivity
import kz.kizirov.core.navigation_compose.ResultNavigation
import trikita.log.Log

abstract class CoreBaseScreen: Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content(){

    }

    open fun onDispose(){}

    open fun isCanCloseScreen(onCanCloseScreen:(Boolean) -> Unit){
        onCanCloseScreen.invoke(true)
    }

    @Composable
    fun SubscribeError(viewModel: CoreBaseViewModel){
        val message = viewModel.showErrorMessageEvent.collectAsStateWithLifecycle().value
        if(message != null){
            Log.e("subscribeError", message)
            LocalContext.current.getActivity()?.showAlert(AlertType.ERROR, message.resolve())
            viewModel.clearError()
        }
    }

    fun getResultScreen():Any?{
        return ResultNavigation.getValue()
    }
}