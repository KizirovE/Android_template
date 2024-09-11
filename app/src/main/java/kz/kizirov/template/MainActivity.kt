package kz.kizirov.template

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.lifecycle.ScreenLifecycleStore
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import kz.kizirov.core.base.CoreBaseActivity
import kz.kizirov.main.MainScreen
import kz.kizirov.template.pincode.PinCode
import kz.kizirov.template.pincode.PinCodeImpl
import kz.kizirov.template.ui.theme.TemplateTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainActivity : CoreBaseActivity(),
        PinCode by PinCodeImpl() {
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            var isShowPinCode by remember { mutableStateOf(false) }
            registerLifecycle(this,
                onShowPinCode = {
                    isShowPinCode = true
                }
            )

            KoinAndroidContext() {
                TemplateTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {

                        Navigator(
                            screen = MainScreen(),
                            content = { navigator ->
                                remember(navigator.lastItem) {
                                    ScreenLifecycleStore.get(navigator.lastItem) {
                                        MyScreenLifecycleOwner()
                                    }
                                }

                                if (isShowPinCode) {
                                    ShowPinCode {
                                        isShowPinCode = false
                                    }
                                } else {
                                    CurrentScreen()
                                }
                            },
                            onBackPressed = {
                                if(isShowPinCode){
                                    if(pinCodeBackPressed()) finish()
                                    false
                                }else{
                                    true
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TemplateTheme {
        Greeting("Android")
    }
}