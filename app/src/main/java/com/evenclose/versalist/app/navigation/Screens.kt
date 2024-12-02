package com.evenclose.versalist.app.navigation

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object ListScreen : Screens("list_screen")
    object SplashScreen : Screens("splash_screen")
    object ReminderScreen : Screens("reminder_screen")
}