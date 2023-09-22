package com.evenclose.versalistpro.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.evenclose.versalistpro.presentation.screens.SplashScreen
import com.evenclose.versalistpro.presentation.screens.list.ListScreen
import com.evenclose.versalistpro.presentation.screens.main.MainScreen
import com.evenclose.versalistpro.presentation.screens.reminder.ReminderScreen

@Composable
fun NavGraph(navController: NavHostController) {

    val tweenDuration = 350

    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(
            route = Screens.SplashScreen.route,
        ) {
            SplashScreen(navController)
        }
        composable(
            route = Screens.MainScreen.route,
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
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                    animationSpec = tween(tweenDuration)
                )
            }

        ) {
            MainScreen(navController)
        }
        composable(
            route = "${Screens.ListScreen.route}/{listId}",
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
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                    animationSpec = tween(tweenDuration)
                )
            }
        ) { navBackstackEntry ->
            val listId = navBackstackEntry.arguments?.getString("listId")
            if (listId != null) {
                val id = listId.toInt()
                ListScreen(navController, id)
            }
        }
        composable(
            route = "${Screens.ReminderScreen.route}/{listId}",
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
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.End,
                    animationSpec = tween(tweenDuration)
                )
            }

        ) { navBackstackEntry ->
            val listId = navBackstackEntry.arguments?.getString("listId")
            if (listId != null) {
                val id = listId.toInt()
                ReminderScreen(navController, id)
            }
        }
    }
}