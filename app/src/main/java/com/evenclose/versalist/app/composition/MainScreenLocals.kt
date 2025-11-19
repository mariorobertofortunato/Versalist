package com.evenclose.versalist.app.composition

import androidx.compose.runtime.staticCompositionLocalOf
import com.evenclose.versalist.app.contracts.MainScreenEvent

val LocalMainScreenEventSink = staticCompositionLocalOf<(MainScreenEvent) -> Unit> {
    error("MainScreenEventSink not provided")
}