package kz.kizirov.core.base

import androidx.activity.ComponentActivity
import kz.alseco.core.base.AlertType

abstract class CoreBaseActivity: ComponentActivity() {

    abstract fun showAlert(type: AlertType, message: String)
}