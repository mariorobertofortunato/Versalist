package com.evenclose.versalist.app.compositions

import androidx.compose.runtime.staticCompositionLocalOf
import com.evenclose.versalist.app.contracts.ListScreenEvent

val LocalCompositionListScreen = staticCompositionLocalOf<(ListScreenEvent) -> Unit> {
    error("ListScreenEventTunnel not provided")
}