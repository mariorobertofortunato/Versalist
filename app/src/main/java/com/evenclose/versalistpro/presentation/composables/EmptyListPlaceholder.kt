package com.evenclose.versalistpro.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.evenclose.versalistpro.R
import com.evenclose.versalistpro.presentation.ui.theme.onLight

@Composable
fun EmptyListPlaceholder(type: String) {
    // TODO sistemare hardcoded strings

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.filesearching))


    val text = when (type) {
        "mainScreenPlaceholder" -> {
            "You currently do not have any lists. Please create one by clicking the button below."
        }
        "listScreenPlaceholder" -> {
            "The current list is empty. Please start by adding an item using the button below."
        }
        else -> {
            "ERROR"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ){
        LottieAnimation(
            modifier = Modifier
                .height(100.dp)
            ,
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = onLight,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
        )
    }
}


@Preview
@Composable
fun EmptyListPlaceholderPreview() {
    EmptyListPlaceholder("roomsPlaceHolder")
}