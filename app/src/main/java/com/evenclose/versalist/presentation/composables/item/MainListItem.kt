package com.evenclose.versalist.presentation.composables.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Diversity1
import androidx.compose.material.icons.outlined.EmojiPeople
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.PriorityHigh
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.evenclose.versalist.R
import com.evenclose.versalist.data.model.ListCategory
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.presentation.composables.dialog.deleteitemdialog.DeleteItemDialog
import com.evenclose.versalist.presentation.navigation.Screens
import com.evenclose.versalist.presentation.ui.theme.background
import com.evenclose.versalist.presentation.ui.theme.dark
import com.evenclose.versalist.presentation.ui.theme.light
import com.evenclose.versalist.presentation.ui.theme.secondary
import com.evenclose.versalist.presentation.ui.theme.secondaryContainer
import com.evenclose.versalist.presentation.viewmodel.ListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainListItem(
    mainListItem: MainListItem,
    navController: NavController,
    listViewModel: ListViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }
    var favouriteStatus by remember { mutableStateOf(mainListItem.isFav) }
    val reminderDate by remember { mutableStateOf(mainListItem.reminderDate) }
    var openDeleteDialog by remember { mutableStateOf(false) }


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
            modifier = Modifier.weight(1f)
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .background(color = background, shape = RoundedCornerShape(12.dp))
                    .border(2.dp, secondary, RoundedCornerShape(12.dp)),
            ) {
                Icon(
                    imageVector = categoryIcon,
                    contentDescription = "Category Icon",
                    tint = dark,
                    modifier = Modifier
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                val categoryName: String
                when (mainListItem.category) {
                    ListCategory.PERSONAL -> {
                        categoryName = stringResource(id = R.string.personal)
                    }

                    ListCategory.WORK -> {
                        categoryName = stringResource(id = R.string.work)
                    }

                    ListCategory.HEALTH -> {
                        categoryName = stringResource(id = R.string.health)
                    }

                    ListCategory.SHOPPING -> {
                        categoryName = stringResource(id = R.string.shopping)
                    }

                    ListCategory.SOCIAL -> {
                        categoryName = stringResource(id = R.string.social)
                    }

                    ListCategory.MISC -> {
                        categoryName = stringResource(id = R.string.misc)
                    }

                    else -> {
                        categoryName = stringResource(id = R.string.misc)
                    }
                }
                Text(
                    text = categoryName + " - " + mainListItem.type,
                    fontSize = 14.sp,
                    color = secondaryContainer,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = mainListItem.name,
                    fontSize = 20.sp,
                    color = dark,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(secondaryContainer)
                .border(1.dp, light, RoundedCornerShape(4.dp))
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.PriorityHigh,
                        contentDescription = "Important Icon",
                        tint = light
                    )
                },
                text = {
                    Text(
                        text = if (favouriteStatus) context.getString(R.string.unmark_as_important) else context.getString(
                            R.string.mark_as_important
                        ),
                        fontSize = 16.sp,
                        color = light
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
/*            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Set Alarm Icon",
                        tint = light
                    )
                },
                text = {
                    Text(
                        text = if (reminderDate != null) context.getString(R.string.modify_alarm) else context.getString(
                            R.string.set_alarm
                        ),
                        fontSize = 16.sp,
                        color = light
                    )
                },
                onClick = {
                    expanded = false
                    navController.navigate(route = "${Screens.ReminderScreen.route}/${mainListItem.id}")
                }
            )*/
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete Icon",
                        tint = light
                    )
                },
                text = {
                    Text(
                        text = stringResource(id = R.string.delete) + " " + mainListItem.name,
                        fontSize = 16.sp,
                        color = light
                    )
                },
                onClick = {
                    expanded = false
                    openDeleteDialog = true
                }
            )
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (favouriteStatus) {
                Box(
                    modifier = Modifier
                        .padding(end = 2.dp)
                        .background(color = secondary, shape = RoundedCornerShape(8.dp))
                        .border(2.dp, background, RoundedCornerShape(8.dp))

                ){
                    Icon(
                        imageVector = Icons.Outlined.PriorityHigh,
                        contentDescription = "Fav Icon",
                        tint = background,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
            if (reminderDate != null) {
                Box(
                    modifier = Modifier
                        .padding(end = 2.dp)
                        .background(color = secondary, shape = RoundedCornerShape(8.dp))
                        .border(2.dp, background, RoundedCornerShape(8.dp))

                ){
                    Icon(
                        imageVector = Icons.Outlined.NotificationsActive,
                        contentDescription = "Alarm Icon",
                        tint = background,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }

        if (openDeleteDialog) {
            DeleteItemDialog(
                mainListItem = mainListItem,
                innerListItem = null,
                onDismiss = {
                    openDeleteDialog = false
                }
            )
        }
    }
}