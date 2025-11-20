package com.evenclose.versalist.app.ui.composables.item.innerlistitem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.compositions.LocalCompositionListScreen
import com.evenclose.versalist.app.contracts.ListScreenEvent
import com.evenclose.versalist.app.ui.theme.primaryBlack_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.data.model.InnerListItem
import com.evenclose.versalist.domain.model.PopupTypes

@Composable
fun InnerListItemDropDown(
    innerListItem: InnerListItem,
    expanded: Boolean,
    onDismissRequest: () -> Unit
){

    val eventTunnel = LocalCompositionListScreen.current

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest()  },
        offset = DpOffset(x = 4.dp, y = 4.dp),
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .background(primaryBlack_Light, RoundedCornerShape(16.dp))
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
                onDismissRequest()
                eventTunnel(ListScreenEvent.ShowPopup(PopupTypes.deleteInnerListItem, innerListItem))
            }
        )
    }
}