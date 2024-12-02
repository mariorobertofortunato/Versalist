package com.evenclose.versalist.presentation.composables.dialog.aboutdialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.evenclose.versalist.R

@Composable
fun AboutDialogHeaderImage(modifier: Modifier) {    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_info))

    LottieAnimation(
        composition = composition,
        //iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}