package kz.kizirov.template.pincode

import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.core.os.ConfigurationCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kz.kizirov.template.ui.theme.TemplateTheme

class PinCodeImpl: PinCode, LifecycleEventObserver {

    private var onShowPinCode:(() -> Unit)? = null
    private var countBackPressed = 0
    override fun registerLifecycle(owner: LifecycleOwner, onShowPinCode:() -> Unit) {
        owner.lifecycle.addObserver(this)
        this.onShowPinCode = onShowPinCode

    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                onShowPinCode?.invoke()
                countBackPressed = 0
            }
            else -> Unit
        }
    }

    @Composable
    override fun ShowPinCode(onEnterPinCode: () -> Unit){
        TemplateTheme {
            Column {
                Button(onClick = { onEnterPinCode.invoke() }) {
                    Text(text = "pinCodeEnter")
                }
            }
        }
    }


    override fun pinCodeBackPressed():Boolean {
        countBackPressed++
        return countBackPressed == 2
    }
}

