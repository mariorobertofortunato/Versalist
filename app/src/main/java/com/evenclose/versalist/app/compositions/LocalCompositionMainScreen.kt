package com.evenclose.versalist.app.compositions

import androidx.compose.runtime.staticCompositionLocalOf
import com.evenclose.versalist.app.contracts.MainScreenEvent

val LocalCompositionMainScreen = staticCompositionLocalOf<(MainScreenEvent) -> Unit> {
    error("MainScreenEventTunnel not provided")
}