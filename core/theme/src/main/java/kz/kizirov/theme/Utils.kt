package kz.alseco.theme

import android.content.res.Configuration
import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun isTablet(): Boolean  {
    val configuration = LocalConfiguration.current
    return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        configuration.screenWidthDp > 840
    } else {
        configuration.screenWidthDp > 600
    }
    return false
}

val Int.px: Int
    get() {
        // Получаем текущую "плотность" (density) экрана.
        val density = Resources.getSystem().displayMetrics.density
        // Умножаем значение (this) на плотность, чтобы получить пиксели.
        return (this * density).toInt()
    }