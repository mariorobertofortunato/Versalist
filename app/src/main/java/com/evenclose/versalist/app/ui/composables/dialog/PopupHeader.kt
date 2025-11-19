package com.evenclose.versalist.app.ui.composables.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.evenclose.versalist.data.model.Popup

@Composable
fun PopupHeader (
    modifier: Modifier = Modifier,
    popupType: Popup
) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(popupType.animationResId))

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )

}
