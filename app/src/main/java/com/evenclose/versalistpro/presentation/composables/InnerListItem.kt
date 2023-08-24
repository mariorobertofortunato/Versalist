package com.evenclose.versalistpro.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalistpro.data.model.InnerListItem
import com.evenclose.versalistpro.data.model.MainListItem
import com.evenclose.versalistpro.presentation.navigation.Screens
import com.evenclose.versalistpro.presentation.ui.theme.white
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel

@Composable
fun InnerListItem(
    innerListItem: InnerListItem,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    var checkStatus by remember { mutableStateOf(innerListItem.isChecked) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(
                // Disable ripple effect because it sucks
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    checkStatus = !checkStatus
                    listViewModel.updateItemCheckStatus(innerListItem.id!!, checkStatus)
                    listViewModel.getCurrentInnerList(innerListItem.mainListId)
                }
            )
    ) {

        Icon(
            imageVector =  if (checkStatus) {
                Icons.Outlined.CheckCircle
            } else {
                Icons.Outlined.RadioButtonUnchecked
            },
            contentDescription = "Check Icon",
            tint = white
        )

        Text(
            text = innerListItem.name,
            fontSize = 16.sp,
            color = white,
            modifier = Modifier.padding(start = 8.dp)
        )

    }
}

@Preview
@Composable
fun InnerListItemPreview(
) {
    InnerListItem(innerListItem = InnerListItem(1, "item prova", false, 1))
}