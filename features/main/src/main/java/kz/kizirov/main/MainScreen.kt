package kz.kizirov.main

import android.os.Parcelable
import android.os.strictmode.UntaggedSocketViolation
import android.widget.Toast
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.parcelize.Parcelize
import kz.alseco.theme.kit.Text_16
import kz.alseco.theme.kit.UiKitButton
import kz.kizirov.core.base.CoreBaseScreen

@Parcelize
class MainScreen : CoreBaseScreen(), Parcelable {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<MainViewModel>()
        val context = LocalContext.current
        val action = viewModel.action.collectAsStateWithLifecycle().value.getValue()
        when(action){
            is MainAction.Default -> {}
            is MainAction.ShowToast -> {
                Toast.makeText(context, action.text.resolve(), Toast.LENGTH_SHORT).show()
            }
        }
        SubscribeError(viewModel)
        val state = viewModel.state.collectAsStateWithLifecycle().value
        MainContent(state = state,
            onEvent = {
                viewModel.sendEvent(it)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainContentPreview() {
    MainContent(MainState.Default, {})
}


@Composable
fun MainContent(
    state: MainState,
    onEvent: (MainEvent) -> Unit
) {
    Column {
        Row  {
            UiKitButton(
                text = "OpenExample", onClick = {
                onEvent(MainEvent.OpenExample)
            })
            UiKitButton(
                text = "ShowToast", onClick = {
                onEvent(MainEvent.ShowToast)
            })
        }

        when (state) {
            is MainState.Default -> {


            }

            is MainState.Dogs -> {
                Text_16(text = state.text.resolve())
                Text_16(text = state.textResId.resolve())
                LazyColumn {
                    items(state.list){
                        Text(text = it.toString())
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}