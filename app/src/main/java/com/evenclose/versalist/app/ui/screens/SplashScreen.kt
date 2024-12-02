package com.evenclose.versalist.app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalist.R
import com.evenclose.versalist.app.common.ViewState
import com.evenclose.versalist.app.navigation.Screens
import com.evenclose.versalist.app.ui.theme.backgroundGradient
import com.evenclose.versalist.app.ui.theme.whiteGradient
import com.evenclose.versalist.app.viewmodel.ListViewModel

/** We made a splash screen in order to load the lists from room,
 * thus ensuring the main list screen to show updated data right when is shown
 * (instead of showing the placeholder while waiting for room to load) */

@Composable
fun SplashScreen(
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel()
) {

    val viewState by listViewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        listViewModel.fetchAllLists()
    }

    LaunchedEffect(viewState) {
        when (viewState) {
            is ViewState.Done -> {
                navController.navigate(route = Screens.MainScreen.route) {
                    popUpTo(Screens.SplashScreen.route) {
                        inclusive = true
                    }
                }
            }
            is ViewState.Error -> {
                // Do nothing
            }
            ViewState.Loading -> {
                // Do nothing
            }
            ViewState.None -> {
                // Do nothing
            }
        }
    }

    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(backgroundGradient)
            )
    ){
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Splash screen Icon",
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(whiteGradient),
                    shape = CircleShape
                )


        )
    }



}

