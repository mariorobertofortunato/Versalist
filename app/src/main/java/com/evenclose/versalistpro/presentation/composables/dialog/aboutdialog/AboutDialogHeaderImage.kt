package com.evenclose.versalistpro.presentation.composables.dialog.aboutdialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.evenclose.versalistpro.R

@Composable
fun AboutDialogHeaderImage(modifier: Modifier) {    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_info))

    LottieAnimation(
        composition = composition,
        //iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}