package kz.alseco.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


private val horizontalPadding = 24.dp
private val horizontalPaddingTablet = 50.dp
@Composable
fun getHorizontalPadding() =
    if(isTablet()) horizontalPaddingTablet
    else horizontalPadding
