package kz.kizirov.main

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kz.kizirov.core.StringResource
import kz.kizirov.core.base.CoreBaseViewModel
import kz.kizirov.domain.example.dogs_usecases.GetAllDogsUseCase
import kz.kizirov.domain.example.dogs_usecases.model.DogModel

interface IMainViewModel {
    val state: StateFlow<MainState>
    val navigationEvent: StateFlow<NavigationEvent>
    fun sendEvent(event: MainEvent)
}

sealed class MainEvent{
    object Back: MainEvent()
    object OpenExample: MainEvent()
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
    class OpenExample: NavigationEvent()
}

sealed class MainState{
    object Default: MainState()
    class Dogs(
        val text: StringResource,
        val textResId: StringResource,
        val list: List<DogModel>): MainState()
}

class MainViewModel(
    private val getAllDogsUseCase: GetAllDogsUseCase
): CoreBaseViewModel(), IMainViewModel {

    private var _state = MutableStateFlow<MainState>(MainState.Default)
    override val state: StateFlow<MainState> = _state.asStateFlow()


    private val _navigationEvent = MutableStateFlow<NavigationEvent>(NavigationEvent.Default())
    override val navigationEvent: StateFlow<NavigationEvent> = _navigationEvent.asStateFlow()

    init {
        screenModelScope.launch {
            getAllDogsUseCase().apply {
                if(isSuccessfull){
                    body.collect {
                        _state.value = MainState.Dogs(
                            StringResource.Text("StringResource.Text"),
                            StringResource.ResId(R.string.title_main_screen),
                            it
                        )
                    }
                }
            }
        }
    }

    override fun sendEvent(event: MainEvent) {
        when(event){
            MainEvent.Back -> {
                _navigationEvent.value = NavigationEvent.Back()
            }

            MainEvent.OpenExample -> {
                _navigationEvent.value = NavigationEvent.OpenExample()
            }
        }
    }
}
