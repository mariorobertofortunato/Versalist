package com.evenclose.versalistpro.presentation.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.evenclose.versalistpro.presentation.ui.theme.white

@Composable
fun MainScreenHeader(
    navController: NavController,
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                //horizontal = 12.dp,
                vertical = 8.dp
            )
    ) {
        Text(
            text = "Versalist Pro",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = white,
            modifier = Modifier.padding(start = 12.dp)
        )
        Box(
            //modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.TopStart)
        ) {
            IconButton(
                onClick = {
                    expanded = true
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "Menu Icon",
                    tint = white
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Help", fontSize = 16.sp) },
                    onClick = { /* TODO */ }
                )
                DropdownMenuItem(
                    text = { Text(text = "About", fontSize = 16.sp) },
                    onClick = { /* TODO */ }
                )
                DropdownMenuItem(
                    text = { Text(text = "Privacy", fontSize = 16.sp) },
                    onClick = { /* TODO*/ }
                )
            }
        }

    }
}