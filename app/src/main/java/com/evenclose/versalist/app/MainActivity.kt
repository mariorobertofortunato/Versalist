package com.evenclose.versalist.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.evenclose.versalist.app.navigation.NavGraph
import com.evenclose.versalist.app.ui.theme.VersalistProTheme
import com.evenclose.versalist.data.DataStore
import com.evenclose.versalist.utils.setLanguage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            VersalistProTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation(
) {

    val context = LocalContext.current
    val dataStore = DataStore(context)
    val language = dataStore.getLanguage.collectAsState("")

    if (language.value == "") {
        context.setLanguage(LocalConfiguration.current.locales.get(0).language, false)
    } else {
        context.setLanguage(language.value, false)
    }

    if (language.value == "ar" || language.value == "iw") {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            NavGraph()
        }
    } else {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            NavGraph()
        }
    }


}


