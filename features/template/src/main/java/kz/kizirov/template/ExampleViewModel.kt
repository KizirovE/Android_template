package kz.kizirov.template

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kz.kizirov.core.base.CoreBaseViewModel
import kz.kizirov.domain.example.dogs_usecases.DeleteAllDogsUseCase
import kz.kizirov.domain.example.dogs_usecases.GetAllDogsUseCase
import kz.kizirov.domain.example.dogs_usecases.LoadDog
import kz.kizirov.domain.example.dogs_usecases.model.DogModel
import trikita.log.Log

interface IExampleViewModel {
    val state: StateFlow<ExampleState>
    val navigationEvent: StateFlow<NavigationEvent>
    fun sendEvent(event: ExampleEvent)
}

sealed class ExampleEvent{
    object Back: ExampleEvent()
    object Add: ExampleEvent()
    object Delete: ExampleEvent()
}

sealed class NavigationEvent{
    private var handled: Boolean = false

    fun getValue(): NavigationEvent {
        if (handled) return Default()
        handled = true
        return this
    }
    class Default: NavigationEvent()
    class Back: NavigationEvent()
}

sealed class ExampleState{
    object Default: ExampleState()
    class Dogs(val dog: List<DogModel>): ExampleState()
}

class ExampleViewModel(
    private val getAllDogsUseCase: GetAllDogsUseCase,
    private val loadDog: LoadDog,
    private val deleteAllDogsUseCase: DeleteAllDogsUseCase
): CoreBaseViewModel(), IExampleViewModel {

    private var _state = MutableStateFlow<ExampleState>(ExampleState.Default)
    override val state: StateFlow<ExampleState> = _state.asStateFlow()


    private val _navigationEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.Default())
    override val navigationEvent: StateFlow<NavigationEvent> = _navigationEvent.asStateFlow()

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
                _navigationEvent.value = NavigationEvent.Back()
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
        }
    }
}
