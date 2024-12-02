package com.evenclose.versalist.app.ui.composables.placeholder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.utils.enums.PlaceholderType

@Composable
fun EmptyListPlaceholder(
    type: PlaceholderType
) {

    val context = LocalContext.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.filesearching))

    val text = when (type) {
        PlaceholderType.PLACEHOLDER_MAIN_SCREEN -> {
            context.getString(R.string.main_screen_placeholder)
        }
        PlaceholderType.PLACEHOLDER_LIST_SCREEN  -> {
            context.getString(R.string.list_screen_placeholder)
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .offset(y = (-64).dp)
            .padding(24.dp)
    ){
        LottieAnimation(
            modifier = Modifier
                .height(160.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = primaryWhite,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
    }
}

@Composable
@Preview
private fun EmptyListPlaceholderPreview() {
    EmptyListPlaceholder(
        type = PlaceholderType.PLACEHOLDER_LIST_SCREEN
    )
}