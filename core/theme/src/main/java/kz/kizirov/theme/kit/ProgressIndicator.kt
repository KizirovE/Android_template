package kz.alseco.theme.kit

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import kz.alseco.theme.colorAccentBlue

@Composable
fun UiKitPorgressIndicator(){
    CircularProgressIndicator(
        color = colorAccentBlue,
        strokeWidth = 2.dp
    )
}