package com.evenclose.versalist.app.ui.composables.item.mainlistitem

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.EventNote
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Diversity1
import androidx.compose.material.icons.outlined.EmojiPeople
import androidx.compose.material.icons.outlined.PriorityHigh
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Spa
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.theme.primaryBlack_Dark
import com.evenclose.versalist.app.ui.theme.primaryBlack_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.ui.theme.primaryWhiteVariant
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.domain.model.CategoryTypeKey

@Composable
fun MainListItem(
    mainListItem: MainListItem,
    onClick: () -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    onClick()
                },
                onLongClick = {
                    expanded = true
                }
            )
    ) {
        val categoryIcon = when (mainListItem.category) {
            CategoryTypeKey.PERSONAL -> {
                Icons.Outlined.EmojiPeople
            }

            CategoryTypeKey.WORK -> {
                Icons.Outlined.Badge
            }

            CategoryTypeKey.HEALTH  -> {
                Icons.Outlined.Spa
            }

            CategoryTypeKey.SHOPPING  -> {
                Icons.Outlined.ShoppingCart
            }

            CategoryTypeKey.SOCIAL  -> {
                Icons.Outlined.Diversity1
            }

            CategoryTypeKey.MISC  -> {
                Icons.AutoMirrored.Outlined.EventNote
            }

            else -> {
                Icons.AutoMirrored.Outlined.List
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
                    .background(color = primaryWhite, shape = CircleShape)
                    .border(2.dp, primaryBlack_Light, CircleShape),
            ) {
                Icon(
                    imageVector = categoryIcon,
                    contentDescription = "Category Icon",
                    tint = primaryBlack_Light
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                val categoryName: String
                when (mainListItem.category) {
                    CategoryTypeKey.PERSONAL  -> {
                        categoryName = stringResource(id = R.string.personal)
                    }

                    CategoryTypeKey.WORK  -> {
                        categoryName = stringResource(id = R.string.work)
                    }

                    CategoryTypeKey.HEALTH  -> {
                        categoryName = stringResource(id = R.string.health)
                    }

                    CategoryTypeKey.SHOPPING  -> {
                        categoryName = stringResource(id = R.string.shopping)
                    }

                    CategoryTypeKey.SOCIAL  -> {
                        categoryName = stringResource(id = R.string.social)
                    }

                    CategoryTypeKey.MISC  -> {
                        categoryName = stringResource(id = R.string.misc)
                    }

                    else -> {
                        categoryName = stringResource(id = R.string.misc)
                    }
                }
                Text(
                    text = categoryName + " - " + mainListItem.type,
                    fontSize = 14.sp,
                    color = primaryWhiteVariant,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = mainListItem.name,
                    fontSize = 20.sp,
                    color = primaryWhite,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        MainListItemDropDown(
            mainListItem = mainListItem,
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            if (mainListItem.isFav) {
                Box(
                    modifier = Modifier
                        .padding(end = 2.dp)
                        .background(color = primaryBlack_Dark, shape = RoundedCornerShape(8.dp))
                        .border(2.dp, primaryWhite, RoundedCornerShape(8.dp))

                ){
                    Icon(
                        imageVector = Icons.Outlined.PriorityHigh,
                        contentDescription = "Fav Icon",
                        tint = primaryWhite,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }


    }
}