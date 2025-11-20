package com.evenclose.versalist.app.ui.composables.item.innerlistitem

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.app.compositions.LocalCompositionListScreen
import com.evenclose.versalist.app.contracts.ListScreenEvent
import com.evenclose.versalist.app.ui.theme.primaryGrey
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.data.model.InnerListItem

@Composable
fun CheckListItem(
    innerListItem: InnerListItem
) {
    val eventTunnel = LocalCompositionListScreen.current
    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    eventTunnel(ListScreenEvent.ToggleInnerListCheckStatus(innerListItem))
                },
                onLongClick = {
                    expanded = true
                }
            )
    ) {

        Icon(
            imageVector = if (innerListItem.isChecked) {
                Icons.Outlined.CheckCircle
            } else {
                Icons.Outlined.Circle
            },
            contentDescription = "Check Icon",
            tint = if (innerListItem.isChecked) {
                primaryGrey
            } else {
                primaryWhite
            }
        )
        Text(
            text = innerListItem.name,
            fontSize = 24.sp,
            color = if (innerListItem.isChecked) {
                primaryGrey
            } else {
                primaryWhite
            },
            fontWeight = FontWeight.Bold,
            style = if (innerListItem.isChecked) {
                TextStyle(textDecoration = TextDecoration.LineThrough)
            } else {
                TextStyle(textDecoration = TextDecoration.None)
            },
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