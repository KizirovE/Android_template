package kz.kizirov.template.pincode

import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleOwner

interface PinCode {
    fun registerLifecycle(owner: LifecycleOwner, onShowPinCode:() -> Unit)
    @Composable
    fun ShowPinCode(onEnterPinCode: () -> Unit)

    fun pinCodeBackPressed(): Boolean
}