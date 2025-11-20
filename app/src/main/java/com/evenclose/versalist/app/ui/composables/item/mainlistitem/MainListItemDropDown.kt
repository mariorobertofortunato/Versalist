package com.evenclose.versalist.app.ui.composables.item.mainlistitem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.PriorityHigh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
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
import com.evenclose.versalist.app.compositions.LocalCompositionMainScreen
import com.evenclose.versalist.app.contracts.MainScreenEvent
import com.evenclose.versalist.app.ui.theme.primaryBlack_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.ui.theme.secondaryBlue
import com.evenclose.versalist.data.model.MainListItem
import com.evenclose.versalist.data.model.PopupTypes
import kotlin.Unit

@Composable
fun MainListItemDropDown(
    mainListItem: MainListItem,
    expanded: Boolean,
    onDismissRequest: () -> Unit
) {

    val context = LocalContext.current
    val eventTunnel = LocalCompositionMainScreen.current

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest() },
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
                    imageVector = Icons.Outlined.PriorityHigh,
                    contentDescription = "Important Icon",
                    tint = primaryWhite
                )
            },
            text = {
                Text(
                    text = if (mainListItem.isFav) context.getString(R.string.unmark_as_important) else context.getString(
                        R.string.mark_as_important
                    ),
                    fontSize = 16.sp,
                    color = primaryWhite
                )
            },
            onClick = {
                onDismissRequest()
                eventTunnel(MainScreenEvent.ToggleMainListItemFav(mainListItem.id ?: 0, !mainListItem.isFav))
            }
        )
        HorizontalDivider(
            color = secondaryBlue.copy(alpha = 0.25f),
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
        )
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
                    text = stringResource(id = R.string.delete) + " " + mainListItem.name,
                    fontSize = 16.sp,
                    color = primaryWhite
                )
            },
            onClick = {
                onDismissRequest()
                eventTunnel(MainScreenEvent.ShowPopup(PopupTypes.deleteMainListItem, mainListItem))
            }
        )
    }

}