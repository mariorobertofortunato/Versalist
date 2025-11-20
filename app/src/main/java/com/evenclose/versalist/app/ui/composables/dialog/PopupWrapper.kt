package com.evenclose.versalist.app.ui.composables.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.composables.VersalistFab
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.domain.model.Popup
import com.evenclose.versalist.domain.model.PopupTypes

@Composable
fun PopupWrapper(
    popupType: Popup?,
    selectedMainListItem: MainListItem? = null,
    selectedInnerListItem: InnerListItem? = null,
    onDismiss: () -> Unit
) {
    val animResId = popupType?.animationResId ?: R.raw.animation_info
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animResId))

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(vertical = 80.dp)
            ) {
                PopupContent(
                    popupType = popupType ?: return@Dialog,
                    selectedMainListItem = selectedMainListItem,
                    selectedInnerListItem = selectedInnerListItem,
                    modifier = Modifier.weight(1f, fill = false)
                )
                VersalistFab(
                    text = stringResource(id = R.string.got_it),
                    onClick = {
                        onDismiss()
                    },
                    modifier = Modifier
                )
            }

            LottieAnimation(
                composition = composition,
                iterations = if (popupType?.infiniteAnimation ?: false) LottieConstants.IterateForever else 1,
                modifier = Modifier.size(140.dp).align(Alignment.TopCenter)
            )

        }

    }
}

@Composable
@Preview
fun PopupWrapperPreview() {
    PopupWrapper(
        popupType = PopupTypes.about,
        selectedMainListItem = null,
        selectedInnerListItem = null,
        onDismiss = {}
    )
}
