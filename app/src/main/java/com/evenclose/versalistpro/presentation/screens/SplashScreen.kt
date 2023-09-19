package com.evenclose.versalistpro.presentation.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FormatListBulleted
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalistpro.presentation.navigation.Screens
import com.evenclose.versalistpro.presentation.ui.theme.light
import com.evenclose.versalistpro.presentation.ui.theme.secondary
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel

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
    ){
        Icon(
            imageVector = Icons.Outlined.FormatListBulleted,
            contentDescription = "Loading Icon",
            tint = light,
            modifier = Modifier
                .size(value.value.dp)
                .background(
                    color = secondary,
                    shape = RoundedCornerShape(12.dp)
                )
        )
    }
}