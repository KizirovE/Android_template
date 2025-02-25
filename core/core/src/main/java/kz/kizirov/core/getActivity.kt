package kz.kizirov.core

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import kz.kizirov.core.base.CoreBaseActivity

fun Context.getActivity(): CoreBaseActivity? = when (this) {
    is ComponentActivity -> this as CoreBaseActivity
    is ContextWrapper -> baseContext.getActivity() as CoreBaseActivity
    else -> null
}