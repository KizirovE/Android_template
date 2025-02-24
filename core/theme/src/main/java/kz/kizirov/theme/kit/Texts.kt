package kz.alseco.theme.kit

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Preview
@Composable
private fun TextPreview(){
    Column {
        Text_24_700(text = "asdf")
        Text_20_700(text = "asdf")
        Text_18_700(text = "asdf")
        Text_18(text = "asdf")
        Text_16(text = "asdf")
        Text_14(text = "asdf")
        Text_12(text = "asdf")
        Text_Log(text = "asd\nf")
    }
}

@Composable
fun Text_24_700(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
){
    Text(
        text, modifier, color,
        fontSize = 24.sp,
        null,
        FontWeight(700),
        null,
        letterSpacing, textDecoration, textAlign, lineHeight, overflow, softWrap,maxLines, minLines, onTextLayout,style
    )
}

@Composable
fun Text_24(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
){
    Text(
        text, modifier, color,
        fontSize = 24.sp,
        null,
        FontWeight(400),
        null,
        letterSpacing, textDecoration, textAlign, lineHeight, overflow, softWrap,maxLines, minLines, onTextLayout,style
    )
}

@Composable
fun Text_20_700(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
){
    Text(
        text, modifier, color,
        fontSize = 20.sp,
        null,
        FontWeight(700),
        null,
        letterSpacing, textDecoration, textAlign, lineHeight, overflow, softWrap,maxLines, minLines, onTextLayout,style
    )
}

@Composable
fun Text_18_700(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
){
    Text(
        text, modifier, color,
        fontSize = 18.sp,
        null,
        FontWeight(700),
        null,
        letterSpacing, textDecoration, textAlign, lineHeight, overflow, softWrap,maxLines, minLines, onTextLayout,style
    )
}

@Composable
fun Text_16(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
){
    Text(
        text, modifier, color,
        fontSize = 16.sp,
        null,
        FontWeight(400),null,
        letterSpacing, textDecoration, textAlign, lineHeight, overflow, softWrap,maxLines, minLines, onTextLayout,style
    )
}

@Composable
fun Text_18(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
){
    Text(
        text, modifier, color,
        fontSize = 18.sp,
        null,
        FontWeight(400),null,
        letterSpacing, textDecoration, textAlign, lineHeight, overflow, softWrap,maxLines, minLines, onTextLayout,style
    )
}

@Composable
fun Text_14(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
){
    Text(
        text, modifier, color,
        fontSize = 14.sp,
        null,
        FontWeight(400),null,
        letterSpacing, textDecoration, textAlign, lineHeight, overflow, softWrap,maxLines, minLines, onTextLayout,style
    )
}

@Composable
fun Text_12(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
){
    Text(
        text, modifier, color,
        fontSize = 12.sp,
        null,
        FontWeight(400),null,
        letterSpacing, textDecoration, textAlign, lineHeight, overflow, softWrap,maxLines, minLines, onTextLayout,style
    )
}
@Composable
fun Text_Log(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = 12.sp,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
){
    Text(
        text, modifier, color,
        fontSize = 12.sp,
        null,
        FontWeight(400),null,
        letterSpacing, textDecoration, textAlign, lineHeight, overflow, softWrap,maxLines, minLines, onTextLayout,style
    )
}
