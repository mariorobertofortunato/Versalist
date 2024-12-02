package com.evenclose.versalist.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalist.R
import com.evenclose.versalist.presentation.navigation.Screens
import com.evenclose.versalist.presentation.ui.theme.backgroundGradient
import com.evenclose.versalist.presentation.ui.theme.primaryWhite
import com.evenclose.versalist.presentation.viewmodel.ListViewModel
import kotlinx.coroutines.delay

/** We made a splash screen in order to load the lists from room,
 * thus ensuring the main list screen to show updated data right when is shown
 * (instead of showing the placeholder while waiting for room to load) */

@Composable
fun SplashScreen(
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel()
) {

    val value = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        listViewModel.fetchAllLists()
        value.animateTo(
            targetValue = 200f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )
        delay(20000)
        navController.navigate(route = Screens.MainScreen.route) {
            popUpTo(Screens.SplashScreen.route) {
                inclusive = true
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
                .size(value.value.dp)
                .background(
                    color = primaryWhite,
                    shape = RoundedCornerShape(16.dp)
                )
        )
    }
}