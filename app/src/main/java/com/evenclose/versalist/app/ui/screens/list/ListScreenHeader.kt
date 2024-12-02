package com.evenclose.versalist.app.ui.screens.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
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
import androidx.navigation.NavController
import com.evenclose.versalist.R
import com.evenclose.versalist.app.ui.composables.dialog.helpdialog.listscreenhelpdialog.ListScreenHelpDialog
import com.evenclose.versalist.app.ui.theme.light
import com.evenclose.versalist.app.ui.theme.primaryGreen_Light
import com.evenclose.versalist.app.ui.theme.primaryWhite
import com.evenclose.versalist.app.ui.theme.secondaryContainer

@Composable
fun ListScreenHeader(
    navController: NavController,
    listName: String
) {

    var expanded by remember { mutableStateOf(false) }

    /** Dialog */
    var openHelpDialog by remember { mutableStateOf(false) }

    Column {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 8.dp
                )
        ) {
            IconButton(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                    contentDescription = "Arrow Back Icon",
                    tint = primaryWhite
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f)
            ) {
                Text(
                    text = listName,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = primaryWhite,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box {
                IconButton(
                    onClick = {
                        expanded = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = "Menu Icon",
                        tint = primaryWhite
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    shape = RoundedCornerShape(16.dp),
                    shadowElevation = 10.dp,
                    modifier = Modifier
                        .background(primaryGreen_Light, RoundedCornerShape(16.dp))
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
                            expanded = false
                            openHelpDialog = true
                        }
                    )
                }
            }

            if (openHelpDialog) {
                ListScreenHelpDialog(
                    onDismiss = {
                        openHelpDialog = false
                    }
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier,
            thickness = 1.dp,
            color = primaryWhite
        )
    }
}