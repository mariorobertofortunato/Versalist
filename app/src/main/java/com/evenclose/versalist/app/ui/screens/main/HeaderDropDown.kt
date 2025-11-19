package com.evenclose.versalist.app.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.evenclose.versalist.R
import com.evenclose.versalist.app.compositions.LocalCompositionMainScreen
import com.evenclose.versalist.app.contracts.MainScreenEvent
import com.evenclose.versalist.app.ui.theme.primaryBlack_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.ui.theme.secondaryBlue
import com.evenclose.versalist.data.model.PopupTypes

@Composable
fun HeaderDropDown(
    expanded: Boolean,
    onDismissRequest: () -> Unit
) {
    val eventTunnel = LocalCompositionMainScreen.current

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            onDismissRequest()
        },
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
                eventTunnel(MainScreenEvent.ShowPopup(PopupTypes.help))
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
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Info Icon",
                    tint = primaryWhite
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.about),
                    fontSize = 16.sp,
                    color = primaryWhite
                )
            },
            onClick = {
                onDismissRequest()
                eventTunnel(MainScreenEvent.ShowPopup(PopupTypes.about))
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
                    imageVector = Icons.Outlined.Policy,
                    contentDescription = "Privacy Icon",
                    tint = primaryWhite
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.privacy),
                    fontSize = 16.sp,
                    color = primaryWhite
                )
            },
            onClick = {
                onDismissRequest()
                eventTunnel(MainScreenEvent.ShowPopup(PopupTypes.privacy))
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
                    imageVector = Icons.Outlined.Language,
                    contentDescription = "Language Icon",
                    tint = primaryWhite
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.language),
                    fontSize = 16.sp,
                    color = primaryWhite
                )
            },
            onClick = {
                onDismissRequest()
                eventTunnel(MainScreenEvent.ShowPopup(PopupTypes.language))
            }
        )
    }
}