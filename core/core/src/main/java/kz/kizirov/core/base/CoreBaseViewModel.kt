package kz.kizirov.core.base

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kz.kizirov.core.StringResource
import kz.kizirov.core.network.models.ErrorModel

abstract class CoreBaseViewModel : ScreenModel {

    protected var onCanCloseScreen: (Boolean) -> Unit = {}

    private val _showErrorMessageEvent = MutableStateFlow<StringResource?>(null)
    val showErrorMessageEvent: StateFlow<StringResource?> = _showErrorMessageEvent

    fun clearError(){
        _showErrorMessageEvent.value = null
    }

    fun createErrorEvent(error: ErrorModel?) {
        _showErrorMessageEvent.value = StringResource.Text(error?.user_msg?:"")
    }

    fun createErrorEvent(error: StringResource){
        _showErrorMessageEvent.value = error
    }
}