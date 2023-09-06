package com.evenclose.versalistpro.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Diversity1
import androidx.compose.material.icons.outlined.EmojiPeople
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.PriorityHigh
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Spa
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalistpro.data.model.ListCategory
import com.evenclose.versalistpro.data.model.MainListItem
import com.evenclose.versalistpro.presentation.composables.dialog.deleteitem.DeleteItemDialog
import com.evenclose.versalistpro.presentation.navigation.Screens
import com.evenclose.versalistpro.presentation.ui.theme.onDark
import com.evenclose.versalistpro.presentation.ui.theme.onLight
import com.evenclose.versalistpro.presentation.ui.theme.secondaryContainer
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
    var openDialog by remember { mutableStateOf(false) }

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
        val typeIcon = if (mainListItem.type == "Open list") {
            Icons.Outlined.List
        } else {
            Icons.Outlined.Checklist
        }
        val categoryIcon = when (mainListItem.category) {
            ListCategory.PERSONAL -> {
                Icons.Outlined.EmojiPeople
            }
            ListCategory.WORK -> {
                Icons.Outlined.Badge
            }
            ListCategory.HEALTH -> {
                Icons.Outlined.Spa
            }
            ListCategory.SHOPPING -> {
                Icons.Outlined.ShoppingCart
            }
            ListCategory.SOCIAL -> {
                Icons.Outlined.Diversity1
            }
            ListCategory.MISC -> {
                Icons.Outlined.EventNote
            }
            else -> {
                Icons.Outlined.List
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                imageVector = categoryIcon,
                contentDescription = "Category Icon",
                tint = onLight
            )
            Text(
                text = mainListItem.name,
                fontSize = 16.sp,
                color = onLight,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(secondaryContainer)
                .border(1.dp, onDark, RoundedCornerShape(4.dp))
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete Icon",
                        tint = onDark
                    )
                },
                text = {
                    Text(
                        text = "Delete ${mainListItem.name}",
                        fontSize = 16.sp,
                        color = onDark
                    )
                },
                onClick = {
                    expanded = false
                    openDialog = true
                }
            )
            val importantText = if (favouriteStatus) "Unmark as important" else "Mark as important"
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.PriorityHigh,
                        contentDescription = "Important Icon",
                        tint = onDark
                    )
                },
                text = {
                    Text(
                        text = importantText,
                        fontSize = 16.sp,
                        color = onDark
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
                imageVector = typeIcon,
                contentDescription = "Type Icon",
                tint = onLight
            )
        }

        if (openDialog) {
            DeleteItemDialog(
                mainListItem = mainListItem,
                innerListItem = null,
                onDismiss = {
                    openDialog = false
                }
            )
        }


    }
}