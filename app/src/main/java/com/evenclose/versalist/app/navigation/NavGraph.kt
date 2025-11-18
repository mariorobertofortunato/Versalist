package com.evenclose.versalist.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.evenclose.versalist.app.ui.screens.list.ListScreen
import com.evenclose.versalist.app.ui.screens.main.MainScreen

@Composable
fun NavGraph() {

    val tweenDuration = 350
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                animationSpec = tween(tweenDuration)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Start,
                animationSpec = tween(tweenDuration)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                animationSpec = tween(tweenDuration)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(tweenDuration)
            )
        }
    ) {
        composable(
            route = Screens.MainScreen.route,
        ) {
            MainScreen(
                onNavigateToListId = { listId ->
                    navController.navigate(route = "${Screens.ListScreen.route}/${listId}")
                }
            )
        }
        composable(
            route = "${Screens.ListScreen.route}/{listId}",
        ) { navBackstackEntry ->
            val listId = navBackstackEntry.arguments?.getString("listId")
            if (listId != null) {
                val id = listId.toInt()
                ListScreen(
                    onNavigateUp = {
                        navController.navigateUp()
                    },
                    listId = id
                )
            }
        }
    }
}