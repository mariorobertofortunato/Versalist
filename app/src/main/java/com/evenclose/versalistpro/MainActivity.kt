package com.evenclose.versalistpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.evenclose.versalistpro.data.DataStore
import com.evenclose.versalistpro.presentation.navigation.NavGraph
import com.evenclose.versalistpro.presentation.setLanguage
import com.evenclose.versalistpro.presentation.ui.theme.VersalistProTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VersalistProTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation(
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val dataStore = DataStore(context)
    val language = dataStore.getLanguage.collectAsState("")

    if (language.value == "") {
        context.setLanguage(context.resources.configuration.locales.get(0).language, false)
    } else {
        context.setLanguage(language.value, false)
    }

    if (language.value == "ar" || language.value == "iw") {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            NavGraph(navController = navController)
        }
    } else {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            NavGraph(navController = navController)
        }
    }


}
