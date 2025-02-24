package kz.kizirov.template

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
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
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kz.alseco.theme.AppTheme
import kz.alseco.theme.colorBackground
import kz.kizirov.core.navigation.INavigation
import kz.kizirov.template.start_screen.StartScreen
import kz.kizirov.template.start_screen.StartScreenImpl
import org.koin.android.ext.android.get

class MainActivity : CoreBaseActivity(),
    PinCode by PinCodeImpl(),
    StartScreen by StartScreenImpl() {
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
                AppTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize().safeDrawingPadding(),
                        color = colorBackground
                    ) {
                        val navigation = get<INavigation>()
                        Navigator(
                            screen = getStartScreen(),
                            content = { navigator ->

                                navigation.init(navigator)

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
                                    //Чекаем можем ли уйти с этого экрана
                                    navigation.canBackPressed()
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
    AppTheme{
        Greeting("Android")
    }
}