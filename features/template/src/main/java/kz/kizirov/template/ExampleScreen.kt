package kz.kizirov.template

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.parcelize.Parcelize
import kz.kizirov.core.base.CoreBaseScreen

@Parcelize
class ExampleScreen : CoreBaseScreen(), Parcelable {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<ExampleViewModel>()
        val navigator = LocalNavigator.currentOrThrow
        val navigationEvent = viewModel.navigationEvent.collectAsStateWithLifecycle().value.getValue()
        when(navigationEvent){
            is NavigationEvent.Default -> {}
            is NavigationEvent.Back -> navigator.pop()
            //is NavigationEvent.AuthRouter -> navigator.push(ScreenRegistry.get(AuthRouter.ProfileScreen()))
        }
        SubscribeError(viewModel)

        val state = viewModel.state.collectAsStateWithLifecycle().value
        ExampleContent(
            state = state,
            onEvent = {
                viewModel.sendEvent(it)
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExampleContentPreview() {
    ExampleContent(
        state = ExampleState.Default,
        onEvent = {

        })
}


@Composable
fun ExampleContent(state: ExampleState, onEvent: (ExampleEvent) -> Unit) {
    Column {
        Row() {
            Button(onClick = { onEvent.invoke(ExampleEvent.Add) }) {
                Text("load")
            }
            Button(onClick = { onEvent.invoke(ExampleEvent.Delete) }) {
                Text("Delete")
            }
        }
        when (state) {
            is ExampleState.Default -> {
            }

            is ExampleState.Dogs -> {
                LazyColumn {
                    items(state.dog){
                        Text(text = it.toString())
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}