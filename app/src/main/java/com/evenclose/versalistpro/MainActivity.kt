package com.evenclose.versalistpro

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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

        //askNotificationPermission()

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

    private fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { _: Boolean ->

        val builder = AlertDialog.Builder(this)
        builder
            .setTitle("Warning")
            .setMessage("Permission Denied")
            .setPositiveButton("Ok") { _, _ ->
            }
            .create()
            .show()
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


