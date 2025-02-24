package kz.kizirov.main

import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.registry.ScreenRegistry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kz.kizirov.core.StringResource
import kz.kizirov.core.base.CoreBaseViewModel
import kz.kizirov.core.navigation.INavigation
import kz.kizirov.domain.example.dogs_usecases.GetAllDogsUseCase
import kz.kizirov.domain.example.dogs_usecases.model.DogModel

interface IMainViewModel {
    val state: StateFlow<MainState>
    val action: StateFlow<MainAction>
    fun sendEvent(event: MainEvent)
}

sealed class MainEvent{
    object Back: MainEvent()
    object OpenExample: MainEvent()
    object ShowToast: MainEvent()
}

sealed class MainAction{
    private var handled: Boolean = false

    fun getValue(): MainAction {
        if (handled) return Default()
        handled = true
        return this
    }
    class Default: MainAction()
    class ShowToast(val text: StringResource): MainAction()
}

sealed class MainState{
    object Default: MainState()
    class Dogs(
        val text: StringResource,
        val textResId: StringResource,
        val list: List<DogModel>): MainState()
}

class MainViewModel(
    private val navigation: INavigation,
    private val getAllDogsUseCase: GetAllDogsUseCase,
): CoreBaseViewModel(), IMainViewModel {

    private var _state = MutableStateFlow<MainState>(MainState.Default)
    override val state: StateFlow<MainState> = _state.asStateFlow()


    private val _action = MutableStateFlow<MainAction>(MainAction.Default())
    override val action: StateFlow<MainAction> = _action.asStateFlow()

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
                navigation.pop()
            }

            MainEvent.OpenExample -> {
                navigation.push(ScreenRegistry.get(MainRouter.OpenExampleScreen))
            }

            MainEvent.ShowToast -> {
                _action.value = MainAction.ShowToast(StringResource.ResId(R.string.showtoast))
            }
        }
    }
}
