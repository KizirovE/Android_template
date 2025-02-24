package kz.alseco.theme.kit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.alseco.theme.colorAccentBlue
import kz.alseco.theme.colorAccentBlueLight
import kz.alseco.theme.colorBackground
import kz.alseco.theme.colorGray
import kz.alseco.theme.colorLightLilac
import kz.alseco.theme.colorLightRed
import kz.alseco.theme.colorRedLight
import kz.alseco.theme.colorTextPrimary
import kz.alseco.theme.colorTextSecondary
import kz.kizirov.theme.R

@Preview(showBackground = true,
    showSystemUi = true,)
@Composable
fun UiKitPreview2() {
    Column {
        UiKitTextField(
            value = "default text",
            onValueChange = {},
            labelText = "labelText"
        )
        UiKitTextField(
            value = "",
            onValueChange = {},
            labelText = "labelText"
        )
        UiKitTextField(
            value = "",
            onValueChange = {},
            placeholder = "placeholder",
            labelText = "labelText"
        )
       /* UiKitTextField(
            value = "",
            onValueChange = {},
            placeholder = "placeholder",
            labelText = "labelText",
            leadingIcon = painterResource(R.drawable.logo_ajk)
        )*/
        UiKitTextField(
            value = "active",
            onValueChange = {},
            labelText = "labelText",
            enabled = false
        )
        UiKitTextField(
            value = "error",
            onValueChange = {},
            isError = true,
            leadingIcon = painterResource(R.drawable.baseline_error_outline_24),
            labelText = "labelText"
        )
        UiKitTextField(
            value = "",
            onValueChange = {},
            placeholder = "placeholder",
            isError = true,
            leadingIcon = painterResource(R.drawable.baseline_error_outline_24),
            trailingIcon = painterResource(R.drawable.baseline_error_outline_24),
            labelText = "labelText"
        )
    }
}

@Composable
fun UiKitTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    input: Boolean = false,
    isError: Boolean = false,
    labelText: String,
    placeholder: String? = null,
    leadingIcon: Painter? = null,
    leadingIconClick: (() -> Unit)? = null,
    trailingIcon: Painter? = null,
    trailingIconClick: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    cursorBrush: Brush = SolidColor(Color.Black),
){

    val Label: @Composable (() -> Unit) = @Composable {
        Text_12(
            text = labelText,
            color =
                    if(isError)
                        colorLightRed
                    else if(!enabled)
                        colorLightLilac
                    else
                        colorTextSecondary
        )
    }

    val textStyle = TextStyle(color =
                                    if(!enabled)
                                        colorLightLilac
                                    else
                                        colorTextPrimary,
        fontSize = 16.sp)
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value
    BasicTextField(
        value,
        onValueChange,
        modifier,
        if(input) false else enabled,
        if(input) true else readOnly,
        textStyle,
        keyboardOptions,
        keyboardActions,
        singleLine,
        maxLines,
        minLines,
        visualTransformation,
        onTextLayout,
        interactionSource,
        cursorBrush,
    ){ innerTextField ->
        Column(
            modifier = Modifier
                .border(width = 1.dp,
                    shape = RoundedCornerShape(16.dp),
                    color =
                            if(isError)
                                colorLightRed
                            else if(isFocused)
                                colorAccentBlue
                            else if(enabled)
                                colorGray
                            else colorAccentBlueLight
                )
                .background(color =
                                    if(!enabled)
                                        colorGray
                                    else if(isError)
                                        colorRedLight
                                    else colorBackground,
                    shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Label()
            Spacer(Modifier.height(4.dp))
            Row(
                Modifier.fillMaxWidth()
            ) {
                if(leadingIcon != null){
                    val modifierLeading = if(leadingIconClick == null) Modifier else Modifier.clickable { leadingIconClick.invoke() }
                    Icon(
                        modifier = modifierLeading.size(24.dp),
                        painter = leadingIcon,
                        contentDescription = null,
                        tint =
                                if(isError)
                                    colorLightRed
                                else if(!enabled)
                                    colorGray
                                else if(isFocused)
                                    colorAccentBlue
                                else
                                    colorTextPrimary
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Box (
                    modifier = Modifier.weight(1f)
                ) {

                        Text_16(
                            text = if(placeholder != null && value.isEmpty()) placeholder else "",
                            color = if(isError) colorLightRed else colorTextSecondary,
                            maxLines = maxLines
                        )
                        innerTextField()
                }
                if(trailingIcon != null){
                    val modifierTrailing = if(trailingIconClick == null) Modifier else Modifier.clickable { trailingIconClick.invoke() }
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        modifier = modifierTrailing.size(24.dp),
                        painter = trailingIcon,
                        contentDescription = null,
                        tint =
                                if(isError)
                                    colorLightRed
                                else if(!enabled)
                                    colorGray
                                else if(isFocused)
                                    colorAccentBlue
                                else
                                    colorTextPrimary
                    )
                }
            }
            /*if(isFocused) {
                HorizontalDivider(color = colorGray)
            }*/
        }
    }
}

@Composable
fun UiKitTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    input: Boolean = false,
    isError: Boolean = false,
    labelText: String,
    placeholder: String? = null,
    leadingIcon: Painter? = null,
    leadingIconClick: (() -> Unit)? = null,
    trailingIcon: Painter? = null,
    trailingIconClick: (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    cursorBrush: Brush = SolidColor(Color.Black),
){

    val Label: @Composable (() -> Unit) = @Composable {
        Text_12(
            text = labelText,
            color =
                    if(isError)
                        colorLightRed
                    else if(!enabled)
                        colorLightLilac
                    else
                        colorTextSecondary
        )
    }

    val textStyle = TextStyle(color =
                                    if(!enabled)
                                        colorLightLilac
                                    else
                                        colorTextPrimary,
        fontSize = 16.sp)
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value
    BasicTextField(
        value,
        onValueChange,
        modifier,
        if(input) false else enabled,
        if(input) true else readOnly,
        textStyle,
        keyboardOptions,
        keyboardActions,
        singleLine,
        maxLines,
        minLines,
        visualTransformation,
        onTextLayout,
        interactionSource,
        cursorBrush,
    ){ innerTextField ->
        Column(
            modifier = Modifier
                .border(width = 1.dp,
                    shape = RoundedCornerShape(16.dp),
                    color =
                            if(isError)
                                colorLightRed
                            else if(isFocused)
                                colorAccentBlue
                            else if(enabled)
                                colorGray
                            else colorAccentBlueLight
                )
                .background(color =
                                    if(!enabled)
                                        colorGray
                                    else if(isError)
                                        colorRedLight
                                    else colorBackground,
                    shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Label()
            Spacer(Modifier.height(4.dp))
            Row(
                Modifier.fillMaxWidth()
            ) {
                if(leadingIcon != null){
                    val modifierLeading = if(leadingIconClick == null) Modifier else Modifier.clickable { leadingIconClick.invoke() }
                    Icon(
                        modifier = modifierLeading.size(24.dp),
                        painter = leadingIcon,
                        contentDescription = null,
                        tint =
                                if(isError)
                                    colorLightRed
                                else if(!enabled)
                                    colorGray
                                else if(isFocused)
                                    colorAccentBlue
                                else
                                    colorTextPrimary
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Box (
                    modifier = Modifier.weight(1f)
                ) {

                        Text_16(
                            text = if(placeholder != null && value.text.isEmpty()) placeholder else "",
                            color = if(isError) colorLightRed else colorTextSecondary,
                            maxLines = maxLines
                        )
                        innerTextField()
                }
                if(trailingIcon != null){
                    val modifierTrailing = if(trailingIconClick == null) Modifier else Modifier.clickable { trailingIconClick.invoke() }
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        modifier = modifierTrailing.size(24.dp),
                        painter = trailingIcon,
                        contentDescription = null,
                        tint =
                                if(isError)
                                    colorLightRed
                                else if(!enabled)
                                    colorGray
                                else if(isFocused)
                                    colorAccentBlue
                                else
                                    colorTextPrimary
                    )
                }
            }
            /*if(isFocused) {
                HorizontalDivider(color = colorGray)
            }*/
        }
    }
}