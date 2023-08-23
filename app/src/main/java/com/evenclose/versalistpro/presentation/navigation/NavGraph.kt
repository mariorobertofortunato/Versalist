package com.evenclose.versalistpro.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.evenclose.versalistpro.presentation.screens.list.ListScreen
import com.evenclose.versalistpro.presentation.screens.main.MainScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route
    ) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController)
        }
        composable(
            route = "${Screens.ListScreen.route}/{listId}",
        ) {navBackstackEntry ->
            val listId = navBackstackEntry.arguments?.getString("listId")
            if (listId != null) {
                val id = listId.toInt()
                ListScreen(navController, id)
            }
        }
    }
}