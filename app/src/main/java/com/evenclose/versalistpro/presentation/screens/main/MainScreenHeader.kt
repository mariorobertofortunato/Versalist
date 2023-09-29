package com.evenclose.versalistpro.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Policy
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.evenclose.versalistpro.R
import com.evenclose.versalistpro.presentation.composables.dialog.aboutdialog.AboutDialog
import com.evenclose.versalistpro.presentation.composables.dialog.languagedialog.LanguageDialog
import com.evenclose.versalistpro.presentation.composables.dialog.mainscreenhelpdialog.MainScreenHelpDialog
import com.evenclose.versalistpro.presentation.composables.dialog.privacydialog.PrivacyDialog
import com.evenclose.versalistpro.presentation.ui.theme.light
import com.evenclose.versalistpro.presentation.ui.theme.secondaryContainer

@Composable
fun MainScreenHeader()
{
    var expanded by remember { mutableStateOf(false) }

    /** Dialog */
    var openHelpDialog by remember { mutableStateOf(false) }
    var openAboutDialog by remember { mutableStateOf(false) }
    var openPrivacyDialog by remember { mutableStateOf(false) }
    var openLanguageDialog by remember { mutableStateOf(false) }


    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp
            )
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = light,
            modifier = Modifier.padding(start = 12.dp)
        )
        Box()
        {
            IconButton(
                onClick = {
                    expanded = true
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "Menu Icon",
                    tint = light
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(secondaryContainer)
                    .border(1.dp, light, RoundedCornerShape(4.dp))
            ) {

                /** HELP */
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Help,
                            contentDescription = "Help Icon",
                            tint = light
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(id = R.string.help),
                            fontSize = 16.sp,
                            color = light
                        )
                    },
                    onClick = {
                        expanded = false
                        openHelpDialog = true
                    }
                )

                /** INFO / ABOUT */
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Info Icon",
                            tint = light
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(id = R.string.about),
                            fontSize = 16.sp,
                            color = light
                        )
                    },
                    onClick = {
                        expanded = false
                        openAboutDialog = true
                    }
                )

                /** PRIVACY */
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Policy,
                            contentDescription = "Privacy Icon",
                            tint = light
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(id = R.string.privacy),
                            fontSize = 16.sp,
                            color = light
                        )
                    },
                    onClick = {
                        expanded = false
                        openPrivacyDialog = true
                    }
                )

                /** LANGUAGE */
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Language,
                            contentDescription = "Language Icon",
                            tint = light
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(id = R.string.language),
                            fontSize = 16.sp,
                            color = light
                        )
                    },
                    onClick = {
                        expanded = false
                        openLanguageDialog = true
                    }
                )
            }
        }

        if (openHelpDialog) {
            MainScreenHelpDialog(
                onDismiss = {
                    openHelpDialog = false
                }
            )
        }
        if (openAboutDialog) {
            AboutDialog(
                onDismiss = {
                    openAboutDialog = false
                }
            )
        }
        if (openPrivacyDialog) {
            PrivacyDialog(
                onDismiss = {
                    openPrivacyDialog = false
                }
            )
        }
        if (openLanguageDialog) {
            LanguageDialog(
                onDismiss = {
                    openLanguageDialog = false
                }
            )
        }

    }
}