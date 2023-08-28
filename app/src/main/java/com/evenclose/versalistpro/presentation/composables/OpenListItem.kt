package com.evenclose.versalistpro.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import com.evenclose.versalistpro.data.model.InnerListItem
import com.evenclose.versalistpro.presentation.ui.theme.white
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OpenListItem(
    innerListItem: InnerListItem,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    var expanded by remember { mutableStateOf(false) }
    //var checkStatus by remember { mutableStateOf(innerListItem.isChecked) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .combinedClickable(
                // Disable ripple effect because it sucks
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { // TODO      eventualmente azione di click su questi item, ma al momento non ce ne sono
                     },
                onLongClick = {
                    expanded = true
                }
            )
    ) {

/*        Icon(
            imageVector =  if (checkStatus) {
                Icons.Outlined.CheckCircle
            } else {
                Icons.Outlined.RadioButtonUnchecked
            },
            contentDescription = "Check Icon",
            tint = white
        )*/

        Text(
            text = innerListItem.name,
            fontSize = 16.sp,
            color = white,
            modifier = Modifier.padding(start = 8.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = "Delete item", fontSize = 16.sp) },
                onClick = {
                    expanded = false
                    listViewModel.deleteInnerListItem(innerListItem.id!!, innerListItem.mainListId)
                }
            )
        }

    }
}
