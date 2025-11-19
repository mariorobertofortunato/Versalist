package com.evenclose.versalist.app.ui.composables.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.data.model.Popup

@Composable
fun PopupWrapper(
    popupType: Popup?,
    selectedMainListItem: MainListItem?,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier.offset(y = (-40).dp)
        ) {
            PopupContent(
                popupType = popupType ?: return@Dialog,
                selectedMainListItem = selectedMainListItem,
                onDismiss = onDismiss
            )
            PopupHeader(
                modifier = Modifier,
                popupType = popupType ?: return@Dialog,
            )
        }

    }
}