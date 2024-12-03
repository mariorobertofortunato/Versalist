package com.evenclose.versalist.app.ui.composables.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Delete
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.composables.dialog.deleteitemdialog.DeleteItemDialog
import com.evenclose.versalist.app.ui.theme.primaryGreen_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.data.model.InnerListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OpenListItem(
    innerListItem: InnerListItem,
) {

    var expanded by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .combinedClickable(
                // Disable ripple effect because it sucks
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { },
                onLongClick = {
                    expanded = true
                }
            )
    ) {
        Icon(
            imageVector = Icons.Outlined.Circle,
            contentDescription = "Bullet Icon",
            tint = Color.Transparent,
            modifier = Modifier
                .size(16.dp)
                .background(primaryWhite, CircleShape)
        )
        Text(
            text = innerListItem.name,
            fontSize = 24.sp,
            color = primaryWhite,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            offset = DpOffset(x = 4.dp, y = 4.dp),
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 10.dp,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(primaryGreen_Light, RoundedCornerShape(16.dp))
                .border(2.dp, primaryWhite, RoundedCornerShape(16.dp))
        ) {
            DropdownMenuItem(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete Icon",
                        tint = primaryWhite
                    )
                },
                text = {
                    Text(
                        text = stringResource(id = R.string.delete) + " " + innerListItem.name,
                        fontSize = 16.sp,
                        color = primaryWhite
                    )
                },
                onClick = {
                    expanded = false
                    openDialog = true
                }
            )
        }

        if (openDialog) {
            DeleteItemDialog(
                mainListItem = null,
                innerListItem = innerListItem,
                onDismiss = {
                    openDialog = false
                }
            )
        }

    }
}
