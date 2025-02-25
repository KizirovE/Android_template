package kz.kizirov.template.example

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kz.kizirov.core.StringResource
import kz.kizirov.core.base.CoreBaseViewModel
import kz.kizirov.core.navigation.INavigation
import kz.kizirov.domain.example.dogs_usecases.DeleteAllDogsUseCase
import kz.kizirov.domain.example.dogs_usecases.GetAllDogsUseCase
import kz.kizirov.domain.example.dogs_usecases.LoadDog
import kz.kizirov.domain.example.dogs_usecases.model.DogModel
import kz.kizirov.template.R
import trikita.log.Log

interface IExampleViewModel {
    val state: StateFlow<ExampleState>
    val action: StateFlow<ExampleActions?>
    fun sendEvent(event: ExampleEvent)
}

sealed class ExampleEvent{
    object Back: ExampleEvent()
    object Add: ExampleEvent()
    object Delete: ExampleEvent()
    object ShowToast: ExampleEvent()
    object ShowAlert: ExampleEvent()

    //ОПЦИОНАЛЬНО, для случаев редактирования формы и хотим спросить "НЕсохраненые изменения будут потеряны. ДА/НЕТ
    //Проверяем можно ли закрыть экран
    class CheckClose(val onCanCloseScreen: (Boolean) -> Unit) : ExampleEvent()
    //Закрываем
    object CanCloseScreen : ExampleEvent()
}

sealed class ExampleActions{
    private var handled: Boolean = false

    fun getValue(): ExampleActions? {
        if (handled) return null
        handled = true
        return this
    }
    class ShowToast(val text: StringResource): ExampleActions()
    class ShowAlert(val text: StringResource): ExampleActions()

    //ОПЦИОНАЛЬНО, для случаев редактирования формы и хотим спросить "НЕсохраненые изменения будут потеряны. ДА/НЕТ
    class ShowCantCloseScreen: ExampleActions()
}

sealed class ExampleState{
    object Default: ExampleState()
    class Dogs(val dog: List<DogModel>): ExampleState()
}

class ExampleViewModel(
    private val getAllDogsUseCase: GetAllDogsUseCase,
    private val loadDog: LoadDog,
    private val deleteAllDogsUseCase: DeleteAllDogsUseCase,
    private val navigation: INavigation
): CoreBaseViewModel(), IExampleViewModel {

    private var _state = MutableStateFlow<ExampleState>(ExampleState.Default)
    override val state: StateFlow<ExampleState> = _state.asStateFlow()


    private val _action = MutableStateFlow<ExampleActions?>(null)
    override val action: StateFlow<ExampleActions?> = _action.asStateFlow()

    init {
        screenModelScope.launch {
            getAllDogsUseCase().apply {
                if(isSuccessfull){
                    body.collect {
                        _state.value = ExampleState.Dogs(it)
                    }
                }
            }
        }
    }

    override fun sendEvent(event: ExampleEvent) {
        when(event){
            ExampleEvent.Back -> {
                navigation.pop()
            }
            ExampleEvent.Add -> {
                screenModelScope.launch {
                    loadDog().apply {
                        if(isSuccessfull){
                            Log.d("successBody", body)
                        }
                        if(isFailed){
                            Log.d("errorBody", failed.message)
                        }
                    }
                }
            }

            ExampleEvent.Delete -> {
                screenModelScope.launch {
                   deleteAllDogsUseCase()
                }
            }

            ExampleEvent.ShowToast -> {
                _action.value = ExampleActions.ShowToast(StringResource.ResId(R.string.toast))
            }


            is ExampleEvent.CheckClose -> {
                onCanCloseScreen = event.onCanCloseScreen
                val functionChackHAveChange =true
                if(functionChackHAveChange) {
                    //Есть измения спрашиваем
                    _action.value = ExampleActions.ShowCantCloseScreen()
                }else{
                    //Если нет, можем закрыть экран
                    onCanCloseScreen.invoke(true)
                }
            }

            ExampleEvent.CanCloseScreen -> {
                //Дергается когда пользователь нажал ДА в диалоге
                onCanCloseScreen.invoke(true)
            }

            ExampleEvent.ShowAlert -> {
                _action.value = ExampleActions.ShowAlert(StringResource.Text("Alert Example"))
            }
        }
    }
}
