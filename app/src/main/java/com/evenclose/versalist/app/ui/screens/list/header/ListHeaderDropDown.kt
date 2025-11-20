package com.evenclose.versalist.app.ui.screens.list.header

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.compositions.LocalCompositionListScreen
import com.evenclose.versalist.app.contracts.ListScreenEvent.*
import com.evenclose.versalist.app.ui.theme.primaryBlack_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.domain.model.PopupTypes

@Composable
fun ListHeaderDropDown(
    expanded: Boolean,
    onDismissRequest: () -> Unit
){

    val eventTunnel = LocalCompositionListScreen.current

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest() },
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 10.dp,
        modifier = Modifier
            .background(primaryBlack_Light, RoundedCornerShape(16.dp))
            .border(2.dp, primaryWhite, RoundedCornerShape(16.dp))
    ) {
        DropdownMenuItem(
            leadingIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Help,
                    contentDescription = "Help Icon",
                    tint = primaryWhite
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.help),
                    fontSize = 16.sp,
                    color = primaryWhite
                )
            },
            onClick = {
                onDismissRequest()
                eventTunnel(ShowPopup(PopupTypes.helpListScreen))
            }
        )
    }
}