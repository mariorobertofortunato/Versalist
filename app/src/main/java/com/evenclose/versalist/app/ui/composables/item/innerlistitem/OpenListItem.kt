package com.evenclose.versalist.app.ui.composables.item.innerlistitem

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.data.model.InnerListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OpenListItem(
    innerListItem: InnerListItem,
) {

    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    // No action available for single tap
                },
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
        InnerListItemDropDown(
            innerListItem = innerListItem,
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        )
    }
}
