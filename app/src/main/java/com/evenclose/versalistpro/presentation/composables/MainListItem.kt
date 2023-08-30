package com.evenclose.versalistpro.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.PriorityHigh
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalistpro.data.model.MainListItem
import com.evenclose.versalistpro.presentation.navigation.Screens
import com.evenclose.versalistpro.presentation.ui.theme.onLight
import com.evenclose.versalistpro.presentation.viewmodel.ListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainListItem(
    mainListItem: MainListItem,
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    var expanded by remember { mutableStateOf(false) }
    var favouriteStatus by remember { mutableStateOf(mainListItem.isFav) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .combinedClickable(
                // Disable ripple effect because it sucks
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    navController.navigate(route = "${Screens.ListScreen.route}/${mainListItem.id}")
                },
                onLongClick = {
                    expanded = true
                }
            )
    ) {
        val icon = if (mainListItem.type == "Open list") {
            Icons.Outlined.List
        } else {
            Icons.Outlined.Checklist
        }

        Text(
            text = "• ${mainListItem.name}",
            fontSize = 16.sp,
            color = onLight,
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = "Delete list", fontSize = 16.sp) },
                onClick = {
                    expanded = false
                    listViewModel.deleteMainListItem(mainListItem.id!!)
                }
            )
            val importantText = if (favouriteStatus) "Unmark as important" else "Mark as important"
            DropdownMenuItem(
                text = {
                    Text(
                        text = importantText,
                        fontSize = 16.sp
                    )
                },
                onClick = {
                    expanded = false
                    favouriteStatus = !favouriteStatus
                    listViewModel.updateMainListFavouriteStatus(
                        mainListItemId = mainListItem.id!!,
                        newFavouriteStatus = favouriteStatus
                    )
                }
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (favouriteStatus) {
                Icon(
                    imageVector = Icons.Outlined.PriorityHigh,
                    contentDescription = "Fav Icon",
                    tint = onLight
                )
            }
            Icon(
                imageVector = icon,
                contentDescription = "Menu Icon",
                tint = onLight
            )
        }


    }
}