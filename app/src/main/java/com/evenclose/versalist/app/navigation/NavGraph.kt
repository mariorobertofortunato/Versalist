package com.evenclose.versalist.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.evenclose.versalist.app.ui.screens.list.ListScreen
import com.evenclose.versalist.app.ui.screens.main.MainScreen

@Composable
fun NavGraph() {

    val tweenDuration = 350
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.MainScreenRoute,
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
        composable<Routes.MainScreenRoute> {
            MainScreen(
                onNavigateToList = { mainListItem ->
                    navController.navigate(
                        route = Routes.ListScreenRoute(
                            mainListItemId = mainListItem.id ?: 0,
                            mainListItemName = mainListItem.name,
                            mainListItemType = mainListItem.type
                        )
                    )
                }
            )
        }
        composable<Routes.ListScreenRoute> { backStackEntry ->
            val args: Routes.ListScreenRoute = backStackEntry.toRoute()
            ListScreen(
                onNavigateUp = {
                    navController.navigateUp()
                },
                mainListItemId = args.mainListItemId,
                mainListItemName = args.mainListItemName,
                mainListItemType = args.mainListItemType
            )
        }
    }
}