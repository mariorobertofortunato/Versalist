package com.evenclose.versalist.app.ui.composables.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.evenclose.versalist.app.ui.composables.dialog.contents.AboutDialog
import com.evenclose.versalist.app.ui.composables.dialog.contents.DeleteInnerItemDialog
import com.evenclose.versalist.app.ui.composables.dialog.contents.DeleteItemDialog
import com.evenclose.versalist.app.ui.composables.dialog.contents.LanguageDialog
import com.evenclose.versalist.app.ui.composables.dialog.contents.ListScreenHelpDialog
import com.evenclose.versalist.app.ui.composables.dialog.contents.MainScreenHelpDialog
import com.evenclose.versalist.app.ui.composables.dialog.contents.PrivacyDialog
import com.evenclose.versalist.app.ui.theme.primaryBlack_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.domain.model.Popup
import com.evenclose.versalist.domain.model.PopupTypes

@Composable
fun PopupContent(
    modifier: Modifier = Modifier,
    popupType: Popup,
    selectedMainListItem: MainListItem?,
    selectedInnerListItem: InnerListItem?
) {

        Column(
            modifier = modifier
                .background(primaryBlack_Light, RoundedCornerShape(16.dp))
                .border(2.dp, primaryWhite, RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                when (popupType) {
                    PopupTypes.helpMainScreen -> {
                        MainScreenHelpDialog()
                    }

                    PopupTypes.helpListScreen -> {
                        ListScreenHelpDialog()
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

                    PopupTypes.deleteInnerListItem -> {
                        DeleteItemDialog(
                            innerListItem = selectedInnerListItem
                        )
                    }
                }
            }

        }

}