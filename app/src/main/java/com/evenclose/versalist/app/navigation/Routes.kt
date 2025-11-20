package com.evenclose.versalist.app.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object MainScreenRoute

    @Serializable
    data class ListScreenRoute(
        val mainListItemId: Int,
        val mainListItemName: String,
        val mainListItemType: String
    )
}