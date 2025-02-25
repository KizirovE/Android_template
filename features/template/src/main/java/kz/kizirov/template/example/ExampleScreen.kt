package kz.kizirov.template.example

import android.os.Parcelable
import android.widget.Toast
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
import cafe.adriel.voyager.koin.getScreenModel
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kz.alseco.core.base.AlertType
import kz.alseco.theme.kit.UiKitButton
import kz.alseco.theme.kit.UiKitButtonOutline
import kz.kizirov.core.base.CoreBaseScreen
import kz.kizirov.core.getActivity

@Parcelize
class ExampleScreen : CoreBaseScreen(), Parcelable {

    @IgnoredOnParcel
    private lateinit var viewModel: ExampleViewModel

    //ОПЦИОНАЛЬНО, если хотим спросить пользователя "Вы точно хотите выйти ДА/НЕТ"
    override fun isCanCloseScreen(onCanCloseScreen: (Boolean) -> Unit) {
        viewModel.sendEvent(ExampleEvent.CheckClose(onCanCloseScreen))
    }

    @Composable
    override fun Content() {
        viewModel = getScreenModel<ExampleViewModel>()
        val action = viewModel.action.collectAsStateWithLifecycle().value?.getValue()
        val context = LocalContext.current
        when(action){
            null -> {}
            is ExampleActions.ShowToast -> {
                Toast.makeText(context, action.text.resolve(), Toast.LENGTH_SHORT).show()
            }
            is ExampleActions.ShowCantCloseScreen -> {
                //Показываем диалог с вопросом Точно закрыть экран
                //Если да
                viewModel.sendEvent(ExampleEvent.CanCloseScreen)
                //Если нет, то ничего не делаем и остаемся на экране
            }

            is ExampleActions.ShowAlert ->
                context.getActivity()?.showAlert(AlertType.SUCCESS, action.text.resolve())
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
            UiKitButton(
                text = "load", onClick = { onEvent.invoke(ExampleEvent.Add) }
            )
            UiKitButton(
                text = "Delete", onClick = { onEvent.invoke(ExampleEvent.Delete) }
            )
            UiKitButtonOutline(
                text = "ShowToast", onClick = { onEvent.invoke(ExampleEvent.ShowToast) }
            )
            UiKitButtonOutline(
                text = "ShowAlert", onClick = { onEvent.invoke(ExampleEvent.ShowAlert) }
            )
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