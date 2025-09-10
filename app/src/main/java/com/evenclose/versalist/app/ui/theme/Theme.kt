package com.evenclose.versalist.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = primaryWhite,
    onPrimary = background,
    primaryContainer = primaryWhite,
    onPrimaryContainer = background,
    inversePrimary = primaryWhite,
    secondary = secondaryBlue,
    onSecondary = background,
    secondaryContainer = secondaryContainer,
    onSecondaryContainer = background,
    tertiary = Color(0xFF388E3C),
    onTertiary = background,
    tertiaryContainer = primaryWhite,
    onTertiaryContainer = background,
    background = Color.Transparent,
    onBackground = background,
    surface = Color(0xFF333333),
    onSurface = background,
    surfaceVariant = Color(0xFF283593),
    onSurfaceVariant = background,
    surfaceTint = Color(0xFF283593),
    inverseSurface = background,
    inverseOnSurface = Color(0xFF333333),
    error = errorColor,
    onError = background,
    errorContainer = errorColor,
    onErrorContainer = background,
    outline = primaryBlack_Light,
    outlineVariant = primaryWhite,
    scrim = Color(0x99000000)
)

@Composable
fun VersalistProTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            dynamicLightColorScheme(context)
        }

        darkTheme -> LightColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}