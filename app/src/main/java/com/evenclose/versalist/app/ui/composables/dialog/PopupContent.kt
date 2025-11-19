package com.evenclose.versalist.app.ui.composables.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.composables.VersalistFab
import com.evenclose.versalist.app.ui.theme.primaryBlack_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.data.model.Popup
import com.evenclose.versalist.data.model.PopupTypes

@Composable
fun PopupContent(
    popupType: Popup,
    selectedMainListItem: MainListItem?,
    onDismiss: () -> Unit
) {

    Column(
        modifier = Modifier.padding(top = 120.dp)
    ) {
        Box(
            modifier = Modifier
                .background(primaryBlack_Light, RoundedCornerShape(16.dp))
                .border(2.dp, primaryWhite, RoundedCornerShape(16.dp))
                .fillMaxWidth()
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .verticalScroll(
                        state = rememberScrollState(),
                        enabled = true
                    )
            ) {

                when (popupType) {
                    PopupTypes.help -> {
                        MainScreenHelpDialog()
                    }

                    PopupTypes.about -> {
                        AboutDialog()
                    }

                    PopupTypes.privacy -> {
                        PrivacyDialog()
                    }

                    PopupTypes.language -> {
                        LanguageDialog()
                    }

                    PopupTypes.deleteMainListItem -> {
                        DeleteItemDialog(
                            mainListItem = selectedMainListItem
                        )
                    }
                }

                VersalistFab(
                    text = stringResource(id = R.string.got_it),
                    onClick = {
                        onDismiss()
                    }
                )
            }
        }
    }
}