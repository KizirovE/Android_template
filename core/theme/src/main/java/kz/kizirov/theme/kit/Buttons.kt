package kz.alseco.theme.kit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kz.alseco.theme.colorAccentBlue
import kz.alseco.theme.colorGray
import kz.alseco.theme.colorLightLilac
import kz.alseco.theme.colorTextPrimary
import kz.alseco.theme.colorWhite

@Preview
@Composable
private fun UiKitButtonPreview(){
    Column {
        UiKitButton(text = "asdf") { }
        UiKitButton(text = "asdf", enabled = false, isLoading = true) { }
        UiKitButton(text = "asdf", enabled = false) { }
        UiKitButton(text = "asdf", isLoading = true) { }
        UiKitButton(text = "asdf", enabled = true, isLoading = true) { }
        UiKitButtonOutline(text = "asdf") { }
        UiKitButtonOutline(text = "asdf", enabled = false, isLoading = true) { }
        UiKitButtonOutline(text = "asdf", enabled = false) { }
        UiKitButtonOutline(text = "asdf", isLoading = true) { }
        UiKitButtonOutline(text = "asdf", enabled = true, isLoading = true) { }
    }
}


@Composable
fun UiKitButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit
){
    val colors: ButtonColors = ButtonDefaults.buttonColors().copy(
        containerColor = colorAccentBlue,
        contentColor = colorWhite,
        disabledContentColor = colorLightLilac,
        disabledContainerColor = colorGray
    )
    Box(
        contentAlignment = Alignment.Center
    ) {
        if(isLoading) {
            UiKitPorgressIndicator()
        }
        Button(
            modifier = modifier,
            enabled = if(isLoading) false else enabled,
            onClick = onClick,
            colors = colors,
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 15.dp, bottom = 15.dp),
            shape = CircleShape
        ) {
            Text_14(
                text = text,
            )
        }
    }
}

@Composable
fun UiKitButtonOutline(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    onClick: () -> Unit
){
    val colors: ButtonColors = ButtonDefaults.outlinedButtonColors().copy(
        containerColor = colorWhite,
        contentColor = colorTextPrimary,
        disabledContentColor = colorLightLilac,
        disabledContainerColor = colorWhite
    )
    Box(
        contentAlignment = Alignment.Center
    ) {
        if(isLoading) {
            UiKitPorgressIndicator()
        }
        OutlinedButton(
            modifier = modifier,
            enabled = if(isLoading) false else enabled,
            onClick = onClick,
            colors = colors,
            border = BorderStroke(1.dp, if(isLoading || !enabled) colorLightLilac else colorTextPrimary),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 15.dp, bottom = 15.dp),
            shape = CircleShape
        ) {
            Text_14(
                text = text,
            )
        }
    }
}
